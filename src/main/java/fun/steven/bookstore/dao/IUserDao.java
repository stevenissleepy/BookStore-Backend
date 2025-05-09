package fun.steven.bookstore.dao;

import fun.steven.bookstore.dto.UpdateUserDto;
import fun.steven.bookstore.dto.UserDto;
import fun.steven.bookstore.entity.User;

public interface IUserDao {
    User add(UserDto user);
    User delete(Long userId);
    User update(UpdateUserDto user);
    User find(Long userId);
    User findByUserName(String username);
}
