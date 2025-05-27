package fun.steven.bookstore.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.dto.user.UserDto;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.repository.UserRepository;

@Repository
public class UserDao implements IUserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setBalance(0.00);

        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);

        return userRepository.save(user);
    }

    @Override
    public User delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return user;
    }

    @Override
    public User update(Long userId, UpdateUserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);

        return user;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found"));
    }

    @Override
    public Cart getCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        return user.getCart();
    }

    @Override
    public Long getCartId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        return user.getCart().getId();
    }
}
