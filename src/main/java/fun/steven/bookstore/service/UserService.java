package fun.steven.bookstore.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.CartDao;
import fun.steven.bookstore.dao.UserDao;
import fun.steven.bookstore.dto.UpdateUserDto;
import fun.steven.bookstore.dto.UserDto;
import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.exception.LoginException;

@Service                                    /* 将该类标记为一个 Spring Bean */
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;                /* 注入 UserDao 依赖 */

    @Autowired
    private CartDao cartDao;                /* 注入 CartDao 依赖 */

    public User add(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userDao.add(user);
        cartDao.add(user);
        return user;
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
            throw new LoginException("Username not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new LoginException("Password is incorrect");
        }
        return user;
    }
}
