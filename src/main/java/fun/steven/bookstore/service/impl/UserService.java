package fun.steven.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.pojo.dto.user.LoginRequest;
import fun.steven.bookstore.pojo.dto.user.RegisterRequest;
import fun.steven.bookstore.pojo.dto.user.SessionDto;
import fun.steven.bookstore.pojo.dto.user.UpdateRequest;
import fun.steven.bookstore.pojo.entity.Cart;
import fun.steven.bookstore.pojo.entity.User;
import fun.steven.bookstore.pojo.entity.UserAuth;
import fun.steven.bookstore.service.IUserService;
import fun.steven.bookstore.utils.BCryptUtils;
import fun.steven.bookstore.utils.exception.LoginException;
import fun.steven.bookstore.utils.exception.RegisterException;
import fun.steven.bookstore.pojo.dto.user.FindUserResponse;
import fun.steven.bookstore.pojo.dto.user.FindUsersResponse;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Value("${default.avatar.base64}")
    private String defaultAvatar;

    public boolean add(RegisterRequest userDto) {
        String username = userDto.getUsername();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String avatar = defaultAvatar;

        // Check if the username already exists
        if (userDao.existsByUsername(username)) {
            throw new RegisterException("用户名已存在");
        }
        // Check if the email already exists
        if (userDao.existsByEmail(email)) {
            throw new RegisterException("邮箱已被注册");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setAvatar(avatar);
        user.setBalance(0);

        UserAuth userAuth = new UserAuth();
        password = BCryptUtils.hashPassword(password, user.getUsername());
        userAuth.setPassword(password);
        userAuth.setState("normal");
        userAuth.setUser(user);
        user.setUserAuth(userAuth);

        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);

        return userDao.save(user);
    }

    public boolean delete(Long userId) {
        return userDao.deleteById(userId);
    }

    public boolean update(UpdateRequest request) {
        User user = userDao.findById(request.getId()).orElseThrow(
                () -> new RuntimeException("User not found"));

        String username = request.getUsername();
        if (username != null && !username.isEmpty()) {
            user.setUsername(username);
        }

        String password = request.getPassword();
        if (password != null && !password.isEmpty()) {
            password = BCryptUtils.hashPassword(password, user.getUsername());
            user.getUserAuth().setPassword(password);
        }

        String avatar = request.getAvatar();
        if (avatar != null && !avatar.isEmpty()) {
            user.setAvatar(avatar);
        }

        return userDao.save(user);
    }

    public boolean banUser(String username) {
        User user = userDao.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found"));
        user.getUserAuth().setState("banned");
        return userDao.save(user);
    }

    public boolean unbanUser(String username) {
        User user = userDao.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found"));
        user.getUserAuth().setState("normal");
        return userDao.save(user);
    }

    public FindUserResponse findUser(Long userId) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        return new FindUserResponse(user);
    }

    public FindUsersResponse findAllUsers() {
        List<Object[]> userInfos = userDao.findAllSafely();
        List<FindUserResponse> users = userInfos.stream()
                .map(info -> new FindUserResponse(
                        1L, // dummy id, not used
                        (String) info[0], // username
                        (String) info[1], // email
                        (String) info[2], // avatar
                        (Integer) info[3], // balance
                        (String) info[4] // state
                ))
                .toList();
        return new FindUsersResponse(users);
    }

    public SessionDto login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        if (username == null || password == null) {
            throw new LoginException("用户名或密码不能为空");
        }

        password = BCryptUtils.hashPassword(password, username);

        if (!userDao.existsByUsername(username)) {
            throw new LoginException("用户名不存在");
        } else if (!userDao.existsByUsernameAndPassword(username, password)) {
            throw new LoginException("密码错误");
        } else if (userDao.existsByUsernameAndBanned(username)) {
            throw new LoginException("您的账号已经被禁用");
        }

        Long userId = userDao.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found")).getId();

        return new SessionDto(userId, username);
    }
}
