package fun.steven.bookstore.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.dto.AddressDto;
import fun.steven.bookstore.dto.UpdateUserDto;
import fun.steven.bookstore.dto.UserDto;
import fun.steven.bookstore.entity.Address;
import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.exception.LoginException;

@Service                                    /* 将该类标记为一个 Spring Bean */
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;                /* 注入 UserDao 依赖 */
    @Autowired
    private ICartDao cartDao;                /* 注入 CartDao 依赖 */

    @Value("${default.avatar.base64}")
    private String defaultAvatarBase64;

    public User add(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setBalance(0.0);                 
        user.setAvatar(defaultAvatarBase64);
        userDao.addUser(user);
        cartDao.addCart(user);
        return user;
    }

    public User delete(Long userId) {
        return userDao.delete(userId);
    }

    public User update(UpdateUserDto userDto) {
        return userDao.update(userDto);
    }

    public User query(Long userId) {
        return userDao.getById(userId);
    }
    
    public User login(String username, String password) {
        User user = null;
        try {
            user = userDao.getByUserName(username);
        } catch (RuntimeException e) {
            throw new LoginException("Username not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new LoginException("Password is incorrect");
        }
        return user;
    }

    public boolean addAddress(AddressDto addressDto) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        User user = userDao.getById(addressDto.getUserId());
        address.setUser(user);
        return userDao.addAddress(address);
    }
}
