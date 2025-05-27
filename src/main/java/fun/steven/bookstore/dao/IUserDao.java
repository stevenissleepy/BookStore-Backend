package fun.steven.bookstore.dao;

import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.dto.user.UserDto;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.User;

public interface IUserDao {
    User addUser(UserDto user);

    User delete(Long userId);

    User getUserById(Long userId);
    User getByUsername(String username);
    Cart getCart(Long userId);
    Long getCartId(Long userId);

    User update(Long userId, UpdateUserDto user);
}
