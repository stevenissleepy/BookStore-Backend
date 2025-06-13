package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.pojo.dto.user.LoginDto;
import fun.steven.bookstore.pojo.dto.user.RegisterDto;
import fun.steven.bookstore.pojo.dto.user.SessionDto;
import fun.steven.bookstore.pojo.dto.user.UpdateUserDto;
import fun.steven.bookstore.pojo.dto.user.GetUserDto;
import fun.steven.bookstore.pojo.dto.user.GetUsersDto;

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

    public GetUserDto update(Long userId, UpdateUserDto userDto) {
        return userDao.update(userId, userDto);
    }

    public boolean banUser(String username) {
        return userDao.banUser(username);
    }

    public boolean unbanUser(String username) {
        return userDao.unbanUser(username);
    }

    public GetUserDto get(Long userId) {
        return userDao.getUserById(userId);
    }

    public GetUsersDto getAll() {
        return userDao.getAllUsers();
    }
    
    public SessionDto login(LoginDto loginDto) {
        return userDao.checkLogin(loginDto);
    }
}
