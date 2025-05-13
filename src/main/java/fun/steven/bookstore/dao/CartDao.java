package fun.steven.bookstore.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.Book;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.CartItem;
import fun.steven.bookstore.exception.CartEmptyException;
import fun.steven.bookstore.repository.CartItemRepository;

@Repository
public class CartDao implements ICartDao {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem addToCart(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem getCartItem(Book book, Cart cart) {
        return cartItemRepository.findByBookIdAndCartId(book.getId(), cart.getId())
                .orElse(null);
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem) != null;
    }

    @Override
    public List<CartItem> getCartItems(Cart cart) {
        return cartItemRepository.findByCartId(cart.getId()).orElseThrow(
                () -> new CartEmptyException());
    }

    @Override
    public boolean clear(Cart cart) {
        List<CartItem> cartItems = getCartItems(cart);
        for (CartItem item : cartItems) {
            cartItemRepository.delete(item);
        }
        return true;
    }
    
}
