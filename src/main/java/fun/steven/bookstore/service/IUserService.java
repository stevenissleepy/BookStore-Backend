package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.user.LoginDto;
import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.dto.user.UserDto;
import fun.steven.bookstore.dto.user.UserInfoDto;

public interface IUserService {
    
    public UserInfoDto add(UserDto userDto);

    public boolean delete(Long userId);

    public UserInfoDto update(Long userId, UpdateUserDto userDto);

    public UserInfoDto query(Long userId);

    public Long login(LoginDto loginDto);
}
