package fun.steven.bookstore.dao;

import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.User;

public interface ICartDao {
    Cart add(User user);
    Cart addToCart(Long userId, Long bookId, Integer quantity);
    Cart deleteFromCart(Long userId, Long bookId, Integer quantity);
}