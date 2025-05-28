package fun.steven.bookstore.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dto.cart.AddCartItemDto;
import fun.steven.bookstore.dto.cart.GetCartDto;
import fun.steven.bookstore.entity.Book;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.CartItem;
import fun.steven.bookstore.repository.BookRepository;
import fun.steven.bookstore.repository.CartRepository;

@Repository
public class CartDao implements ICartDao {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean addToCart(Long cartId, AddCartItemDto cartItemDto) {
        Long bookId = cartItemDto.getBookId();
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found: " + cartId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found: " + bookId));

        // 在cart的cartItems集合中查找是否已存在该商品
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem item : cartItems) {
            if (item.getBook().getId().equals(bookId)) {
                // 如果已存在，则更新数量
                item.setQuantity(item.getQuantity() + cartItemDto.getQuantity());
                cart.setCartItems(cartItems);
                cartRepository.save(cart);
                return true;
            }
        }

        // 否则创建新的CartItem并添加到购物车
        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setBook(book);
        newCartItem.setQuantity(cartItemDto.getQuantity());
        cartItems.add(newCartItem);
        cart.setCartItems(cartItems);
        cartRepository.save(cart);
        
        return true;
    }

    @Override
    public GetCartDto getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found: " + cartId));
        return new GetCartDto(cart);
    }

    @Override
    public boolean deleteFromCart(Long cartId, Long bookId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found: " + cartId));
        List<CartItem> cartItems = cart.getCartItems();
        
        // 查找并删除指定的CartItem
        cartItems.removeIf(item -> item.getBook().getId().equals(bookId));
        cart.setCartItems(cartItems);
        cartRepository.save(cart);
        
        return true;
    }
}
