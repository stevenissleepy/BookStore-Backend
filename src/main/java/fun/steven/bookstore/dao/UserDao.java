package fun.steven.bookstore.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.repository.CartRepository;
import fun.steven.bookstore.repository.UserRepository;

@Repository
public class UserDao implements IUserDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public User addUser(User user) {
        // 保存用户
        User savedUser = userRepository.save(user);

        // 创建购物车
        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepository.save(cart);

        // 设置用户的购物车
        savedUser.setCart(cart);
        return savedUser;
    }

    @Override
    public User delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return user;
    }

    @Override
    public User update(UpdateUserDto userDto) {
        Long userId = userDto.getUserId();
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
    public User getByUserName(String username) {
        return userRepository.findByUserName(username).orElseThrow(
                () -> new RuntimeException("User not found"));
    }

    @Override
    public Cart getCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        return user.getCart();
    }
}
