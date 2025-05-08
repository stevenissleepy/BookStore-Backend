package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.User;
import fun.steven.bookstore.pojo.dto.UserDto;

public interface IUserDao {
    User add(UserDto user);
    User delete(String userId);
    User update(UserDto user);
    User query(String userId);
}
