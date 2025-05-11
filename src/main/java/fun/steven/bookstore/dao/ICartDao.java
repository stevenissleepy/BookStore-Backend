package fun.steven.bookstore.dao;

import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.CartItem;
import fun.steven.bookstore.entity.User;

public interface ICartDao {
    boolean add(User user);
    boolean addToCart(CartItem cartItem);
    Cart getCartById(Long cartId);
}