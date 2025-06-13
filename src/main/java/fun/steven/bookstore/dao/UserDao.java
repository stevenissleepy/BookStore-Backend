package fun.steven.bookstore.dao;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import fun.steven.bookstore.pojo.dto.user.LoginDto;
import fun.steven.bookstore.pojo.dto.user.RegisterDto;
import fun.steven.bookstore.pojo.dto.user.SessionDto;
import fun.steven.bookstore.pojo.dto.user.UpdateUserDto;
import fun.steven.bookstore.pojo.dto.user.GetUserDto;
import fun.steven.bookstore.pojo.dto.user.GetUsersDto;
import fun.steven.bookstore.pojo.entity.Cart;
import fun.steven.bookstore.pojo.entity.User;
import fun.steven.bookstore.pojo.entity.UserAuth;
import fun.steven.bookstore.repository.UserRepository;
import fun.steven.bookstore.utils.exception.LoginException;
import fun.steven.bookstore.utils.exception.RegisterException;

@Repository
public class UserDao implements IUserDao {
    @Autowired
    private UserRepository userRepository;

    @Value("${default.avatar.base64}")
    private String defaultAvatar;

    @Override
    public boolean addUser(RegisterDto userDto) {
        // Check if the username already exists
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new RegisterException("用户名已存在");
        }
        // Check if the email already exists
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RegisterException("邮箱已被注册");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAvatar(defaultAvatar);
        user.setBalance(0);

        UserAuth userAuth = new UserAuth();
        String password = userDto.getPassword();
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        userAuth.setPassword(password);
        userAuth.setState("normal");
        userAuth.setUser(user);
        user.setUserAuth(userAuth);

        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);

        user = userRepository.save(user);
        return true;
    }

    @Override
    public boolean delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return true;
    }

    @Override
    public GetUserDto update(Long userId, UpdateUserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        String username = userDto.getUsername();
        if (username != null && !username.isEmpty()) {
            user.setUsername(username);
        }

        String password = userDto.getPassword();
        if (password != null && !password.isEmpty()) {
            user.getUserAuth().setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        }

        String avatar = userDto.getAvatar();
        if (avatar != null && !avatar.isEmpty()) {
            user.setAvatar(avatar);
        }

        user = userRepository.save(user);
        return new GetUserDto(user);
    }

    @Override
    public GetUserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        return new GetUserDto(user);
    }

    @Override
    public GetUsersDto getAllUsers() {
        List<Object[]> userInfos = userRepository.findAllUsersSafely();
        List<GetUserDto> users = userInfos.stream()
                .map(info -> new GetUserDto(
                        (String) info[0],  // username
                        (String) info[1],  // email
                        (String) info[2],  // avatar
                        (Integer) info[3], // balance
                        (String) info[4]   // state
                ))
                .toList();
        return new GetUsersDto(users);
    }

    @Override
    public Long getCartId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        return user.getCart().getId();
    }

    @Override
    public SessionDto checkLogin(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new LoginException("User not found"));

        if (!BCrypt.checkpw(password, user.getUserAuth().getPassword())) {
            throw new LoginException("Incorrect password");
        }

        return new SessionDto(user);
    }
}
