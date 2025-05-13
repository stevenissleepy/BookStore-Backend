package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.entity.Book;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.CartItem;

public interface ICartDao {
    CartItem addToCart(CartItem cartItem);

    boolean clear(Cart cart);

    CartItem getCartItem(Book book, Cart cart);
    List<CartItem> getCartItems(Cart cart);
    
    boolean updateCartItem(CartItem cartItem);
}