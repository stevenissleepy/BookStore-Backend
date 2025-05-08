package fun.steven.bookstore.service;

import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.entity.dto.UserDto;

public interface IUserService {
    
    /**
     * @brief 增加用户
     * 
     * @param userDto 用户数据传输对象
     * @return User 返回用户对象
     */
    public User add(UserDto userDto);

    /**
     * @brief 删除用户
     * 
     * @param userDto 用户数据传输对象，只需要用户 ID
     * @return User 返回用户对象
     */
    public User delete(Long userId);

    /**
     * @brief 更新用户
     * 
     * @param userDto 用户数据传输对象，需要用户 ID 和其他信息
     * @return User 返回用户对象
     */
    public User update(UserDto userDto);

    /**
     * @brief 查询用户
     * 
     * @param userDto 用户数据传输对象，只需要用户 ID
     * @return User 返回用户对象
     */
    public User query(Long userId);
}
