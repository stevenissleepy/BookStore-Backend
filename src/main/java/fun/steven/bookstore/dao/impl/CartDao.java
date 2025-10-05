package fun.steven.bookstore.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.pojo.entity.Cart;
import fun.steven.bookstore.repository.CartRepository;

@Repository
public class CartDao implements ICartDao {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public boolean save(Cart cart) {
        return cartRepository.save(cart) != null;
    }

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
