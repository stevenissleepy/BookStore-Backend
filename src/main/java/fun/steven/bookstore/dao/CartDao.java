package fun.steven.bookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.CartItem;
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
    public boolean add(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart) != null;
    }

    @Override
    public boolean addToCart(CartItem cartItem) {
        return cartItemRepository.save(cartItem) != null;
    }
}
