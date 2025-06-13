package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.user.LoginDto;
import fun.steven.bookstore.pojo.dto.user.RegisterDto;
import fun.steven.bookstore.pojo.dto.user.SessionDto;
import fun.steven.bookstore.pojo.dto.user.UpdateUserDto;
import fun.steven.bookstore.pojo.dto.user.GetUserDto;
import fun.steven.bookstore.pojo.dto.user.GetUsersDto;

public interface IUserService {
    
    public boolean add(RegisterDto userDto);

    public boolean delete(Long userId);

    public GetUserDto update(Long userId, UpdateUserDto userDto);
    public boolean banUser(String username);
    public boolean unbanUser(String username);

    public GetUserDto get(Long userId);
    public GetUsersDto getAll();

    public SessionDto login(LoginDto loginDto);
}
