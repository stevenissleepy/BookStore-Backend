package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.UserDao;
import fun.steven.bookstore.dto.UpdateUserDto;
import fun.steven.bookstore.dto.UserDto;
import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.exception.LoginException;

@Service                                    /* 将该类标记为一个 Spring Bean */
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;                /* 注入 UserDao 依赖 */

    public User add(UserDto userDto) {
        return userDao.add(userDto);
    }

    public User delete(Long userId) {
        return userDao.delete(userId);
    }

    public User update(UpdateUserDto userDto) {
        return userDao.update(userDto);
    }

    public User query(Long userId) {
        return userDao.find(userId);
    }
    
    public User login(String username, String password) {
        User user = null;
        try {
            user = userDao.findByUserName(username);
        } catch (RuntimeException e) {
            throw LoginException.usernameError();
        }
        if (!user.getPassword().equals(password)) {
            throw LoginException.passwordError();
        }
        return user;
    }
}
