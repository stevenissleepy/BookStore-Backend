package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.UserDao;
import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.entity.dto.UserDto;

@Service                                    /* 将该类标记为一个 Spring Bean */
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;                /* 注入 UserDao 依赖 */

    public User add(UserDto userDto) {
        return userDao.add(userDto);
    }

    public User delete(String userId) {
        return userDao.delete(userId);
    }

    public User update(UserDto userDto) {
        return userDao.update(userDto);
    }

    public User query(String userId) {
        return userDao.query(userId);
    }
}
