package fun.steven.bookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.repository.CartItemRepository;
import fun.steven.bookstore.repository.CartRepository;

@Repository
public class CartDao implements ICartDao {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Cart add(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addToCart(Long userId, Long bookId, Integer quantity) {
        cartItemRepository.findById(bookId).orElseThrow(
                () -> new RuntimeException("Book not found"));

        return null;
    }

    @Override
    public Cart deleteFromCart(Long userId, Long bookId, Integer quantity) {
        return null;
    }
}
