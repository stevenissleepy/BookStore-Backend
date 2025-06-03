package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.user.LoginDto;
import fun.steven.bookstore.dto.user.SessionDto;
import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.dto.user.UserDto;
import fun.steven.bookstore.dto.user.UserInfoDto;

public interface IUserService {
    
    public boolean add(UserDto userDto);

    public boolean delete(Long userId);

    public UserInfoDto update(Long userId, UpdateUserDto userDto);

    public UserInfoDto query(Long userId);

    public SessionDto login(LoginDto loginDto);
}
