package fun.steven.bookstore.dao;

import fun.steven.bookstore.dto.UpdateUserDto;
import fun.steven.bookstore.entity.Address;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.User;

public interface IUserDao {
    User addUser(User user);
    boolean addAddress(Address address);

    User delete(Long userId);

    /* 查找 */
    User getById(Long userId);
    User getByUserName(String username);
    Cart getCart(Long userId);

    User update(UpdateUserDto user);
}
