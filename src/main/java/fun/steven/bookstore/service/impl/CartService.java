package fun.steven.bookstore.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.pojo.dto.cart.AddToCartRequest;
import fun.steven.bookstore.pojo.dto.cart.FindCartResponse;
import fun.steven.bookstore.pojo.dto.cart.UpdateCartRequest;
import fun.steven.bookstore.pojo.entity.Book;
import fun.steven.bookstore.pojo.entity.Cart;
import fun.steven.bookstore.pojo.entity.CartItem;
import fun.steven.bookstore.service.ICartService;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IBookDao bookDao;

    @Override
    public boolean addToCart(AddToCartRequest request) {
        Long bookId = request.getBookId();
        Long userId = request.getUserId();
        Integer quantity = request.getQuantity();
        Cart cart = cartDao.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));
        Book book = bookDao.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found: " + bookId));

        // 检查书籍是否已经下架
        if (book.getDeleted()) {
            throw new RuntimeException("这本书已下架");
        }

        // 在cart的cartItems集合中查找是否已存在该商品
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem item : cartItems) {
            // 如果已存在，则更新数量
            if (item.getBook().getId().equals(bookId)) {
                item.setQuantity(item.getQuantity() + quantity);
                cart.setCartItems(cartItems);
                cartDao.save(cart);
                return true;
            }
        }

        // 否则创建新的CartItem并添加到购物车
        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setBook(book);
        newCartItem.setQuantity(quantity);
        cartItems.add(newCartItem);
        cart.setCartItems(cartItems);
        cartDao.save(cart);

        return true;
    }

    @Override
    public boolean updateCartItem(UpdateCartRequest cartItemDto) {
        Long bookId = cartItemDto.getBookId();
        Long userId = cartItemDto.getUserId();
        Cart cart = cartDao.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));
        List<CartItem> cartItems = cart.getCartItems();

        // 查找并更新指定的CartItem
        for (CartItem item : cartItems) {
            if (item.getBook().getId().equals(bookId)) {
                item.setQuantity(cartItemDto.getQuantity());
                cart.setCartItems(cartItems);
                cartDao.save(cart);
                return true;
            }
        }

        throw new RuntimeException("book: " + bookId + " not found in cart for user: " + userId);
    }

    @Override
    public FindCartResponse findUserCart(Long userId) {
        Cart cart = cartDao.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        // 检查并移除已下架的书籍
        boolean cartModified = false;
        Iterator<CartItem> iterator = cart.getCartItems().iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getBook().getDeleted()) {
                iterator.remove();
                cartModified = true;
            }
        }

        // 如果购物车内容有变动，则更新数据库
        if (cartModified) {
            cartDao.save(cart);
        }

        // 使用清理后的购物车构建响应
        return new FindCartResponse(cart);
    }
}
