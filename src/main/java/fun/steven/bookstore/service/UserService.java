package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.dto.user.LoginDto;
import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.dto.user.UserDto;
import fun.steven.bookstore.dto.user.UserInfoDto;

@Service                                    /* 将该类标记为一个 Spring Bean */
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;                /* 注入 UserDao 依赖 */
    @Value("${default.avatar.base64}")
    private String defaultAvatarBase64;

    public UserInfoDto add(UserDto userDto) {
        // 如果头像为空，则设置为默认头像
        if(userDto.getAvatar() == null || userDto.getAvatar().isEmpty()) {
            userDto.setAvatar(defaultAvatarBase64);
        }
        
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
    
    public Long login(LoginDto loginDto) {
        return userDao.checkLogin(loginDto);
    }
}
