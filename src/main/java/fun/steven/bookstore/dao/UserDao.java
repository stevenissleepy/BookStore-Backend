package fun.steven.bookstore.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dto.UpdateUserDto;
import fun.steven.bookstore.entity.Address;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.repository.AddressRepository;
import fun.steven.bookstore.repository.UserRepository;

@Repository
public class UserDao implements IUserDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Override
    public User addUser(User user) {
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
    public User update(UpdateUserDto userDto) {
        Long userId = userDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);

        return user;
    }

    @Override
    public User getById(Long userId) {
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

    @Override
    public boolean addAddress(Address address) {
        return addressRepository.save(address) != null;
    }
}
