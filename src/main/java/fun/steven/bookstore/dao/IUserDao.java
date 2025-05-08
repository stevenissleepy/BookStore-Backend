package fun.steven.bookstore.dao;

import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.entity.dto.UserDto;

public interface IUserDao {
    User add(UserDto user);
    User delete(Long userId);
    User update(UserDto user);
    User query(Long userId);
}
