package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.AddressDto;
import fun.steven.bookstore.dto.UpdateUserDto;
import fun.steven.bookstore.dto.UserDto;
import fun.steven.bookstore.entity.User;

public interface IUserService {
    
    /**
     * @brief 增加用户
     * 
     * @param userDto 用户数据传输对象
     * @return User 返回新创建用户
     */
    public User add(UserDto userDto);

    /**
     * @brief 删除用户
     * 
     * @param userId 用户 ID
     * @return User
     */
    public User delete(Long userId);

    /**
     * @brief 更新用户
     * 
     * @param userDto UpdateUserDto
     * @return User 返回更新后用户对象
     */
    public User update(UpdateUserDto userDto);

    /**
     * @brief 查询用户
     * 
     * @param userId 用户 ID
     * @return User 返回用户对象
     */
    public User query(Long userId);

    /**
     * @brief 验证用户登录
     * 
     * @param username 用户名
     * @param password 密码
     * @return 是否登录成功
     */
    public User login(String username, String password);

    public boolean addAddress(AddressDto addressDto);
}
