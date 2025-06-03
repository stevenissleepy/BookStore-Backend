package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.dto.user.LoginDto;
import fun.steven.bookstore.dto.user.SessionDto;
import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.dto.user.RegisterDto;
import fun.steven.bookstore.dto.user.UserInfoDto;

@Service                                    /* 将该类标记为一个 Spring Bean */
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;                /* 注入 UserDao 依赖 */

    public boolean add(RegisterDto userDto) {
        return userDao.addUser(userDto);
    }

    public boolean delete(Long userId) {
        userDao.delete(userId);
        return true;
    }

    public UserInfoDto update(Long userId, UpdateUserDto userDto) {
        return userDao.update(userId, userDto);
    }

    public UserInfoDto query(Long userId) {
        return userDao.getUserById(userId);
    }
    
    public SessionDto login(LoginDto loginDto) {
        return userDao.checkLogin(loginDto);
    }
}
