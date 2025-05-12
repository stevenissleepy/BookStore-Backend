package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.entity.Book;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.CartItem;

public interface ICartDao {
    CartItem addToCart(CartItem cartItem);

    boolean clear(Cart cart);

    CartItem findCartItem(Book book, Cart cart);
    List<CartItem> findCartItems(Cart cart);
    
    boolean updateCartItem(CartItem cartItem);
}