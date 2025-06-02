package fun.steven.bookstore.dao;

import fun.steven.bookstore.dto.user.LoginDto;
import fun.steven.bookstore.dto.user.SessionDto;
import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.dto.user.UserDto;
import fun.steven.bookstore.dto.user.UserInfoDto;

public interface IUserDao {
    UserInfoDto addUser(UserDto user);

    boolean delete(Long userId);

    UserInfoDto getUserById(Long userId);
    Long getCartId(Long userId);

    UserInfoDto update(Long userId, UpdateUserDto user);

    SessionDto checkLogin(LoginDto loginDto);
}
