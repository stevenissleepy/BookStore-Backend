package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.user.LoginDto;
import fun.steven.bookstore.pojo.dto.user.RegisterDto;
import fun.steven.bookstore.pojo.dto.user.SessionDto;
import fun.steven.bookstore.pojo.dto.user.UpdateUserDto;
import fun.steven.bookstore.pojo.dto.user.UserInfoDto;

public interface IUserService {
    
    public boolean add(RegisterDto userDto);

    public boolean delete(Long userId);

    public UserInfoDto update(Long userId, UpdateUserDto userDto);

    public UserInfoDto query(Long userId);

    public SessionDto login(LoginDto loginDto);
}
