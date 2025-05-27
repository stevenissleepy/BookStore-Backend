package fun.steven.bookstore.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.dto.user.UserDto;
import fun.steven.bookstore.dto.user.UserInfoDto;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.repository.UserRepository;

@Repository
public class UserDao implements IUserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfoDto addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setBalance(0.00);

        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);

        user = userRepository.save(user);
        return new UserInfoDto(user);
    }

    @Override
    public boolean delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return true;
    }

    @Override
    public UserInfoDto update(Long userId, UpdateUserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(userDto, user);
        user = userRepository.save(user);

        return new UserInfoDto(user);
    }

    @Override
    public UserInfoDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        return new UserInfoDto(user);
    }

    @Override
    public UserInfoDto getByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found"));

        return new UserInfoDto(user);
    }

    @Override
    public Long getCartId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        return user.getCart().getId();
    }
}
