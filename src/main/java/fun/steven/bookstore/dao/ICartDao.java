package fun.steven.bookstore.dao;

import fun.steven.bookstore.entity.Book;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.CartItem;
import fun.steven.bookstore.entity.User;

public interface ICartDao {
    boolean add(User user);
    boolean addToCart(CartItem cartItem);
    CartItem findCartItem(Book book, Cart cart);
    boolean updateCartItem(CartItem cartItem);
}