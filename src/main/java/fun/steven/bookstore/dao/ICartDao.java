package fun.steven.bookstore.dao;


import java.util.Optional;

import fun.steven.bookstore.pojo.entity.Cart;

public interface ICartDao {
    boolean save(Cart cart);

    Optional<Cart> findByUserId(Long userId);
}