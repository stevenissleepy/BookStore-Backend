package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.dto.user.LoginDto;
import fun.steven.bookstore.pojo.dto.user.RegisterDto;
import fun.steven.bookstore.pojo.dto.user.SessionDto;
import fun.steven.bookstore.pojo.dto.user.UpdateUserDto;
import fun.steven.bookstore.pojo.dto.user.GetUserDto;
import fun.steven.bookstore.pojo.dto.user.GetUsersDto;

public interface IUserDao {
    boolean addUser(RegisterDto user);

    boolean delete(Long userId);

    GetUserDto getUserById(Long userId);
    GetUsersDto getAllUsers();
    Long getCartId(Long userId);

    GetUserDto update(Long userId, UpdateUserDto user);
    boolean banUser(String username);
    boolean unbanUser(String username);

    SessionDto checkLogin(LoginDto loginDto);
}
