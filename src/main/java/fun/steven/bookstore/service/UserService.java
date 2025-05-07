package fun.steven.bookstore.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.pojo.User;
import fun.steven.bookstore.pojo.dto.UserDto;
import fun.steven.bookstore.repository.UserRepository;

@Service                                    /* 将该类标记为一个 Spring Bean */
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;  /* 注入用户仓库 */

    public User add(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userRepository.save(user);
    }

    public User delete(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        return user;
    }

    public User update(UserDto userDto) {
        String userId = userDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);

        return user;
    }

    public User query(String userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
    }
}
