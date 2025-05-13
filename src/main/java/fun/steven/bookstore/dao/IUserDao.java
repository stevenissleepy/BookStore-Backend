package fun.steven.bookstore.dao;

import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.User;

public interface IUserDao {
    User addUser(User user);

    User delete(Long userId);

    User getUserById(Long userId);
    User getByUserName(String username);
    Cart getCart(Long userId);

    User update(UpdateUserDto user);
}
