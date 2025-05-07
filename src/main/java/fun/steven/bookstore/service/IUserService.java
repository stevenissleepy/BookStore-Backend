package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.User;
import fun.steven.bookstore.pojo.dto.UserDto;

public interface IUserService {
    
    /**
     * @brief 增加用户
     * 
     * @param userDto 用户数据传输对象
     * @return User 返回用户对象
     */
    public User add(UserDto userDto);
}
