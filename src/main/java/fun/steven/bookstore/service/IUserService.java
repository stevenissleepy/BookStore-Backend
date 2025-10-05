package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.user.LoginRequest;
import fun.steven.bookstore.pojo.dto.user.RegisterRequest;
import fun.steven.bookstore.pojo.dto.user.SessionDto;
import fun.steven.bookstore.pojo.dto.user.UpdateRequest;
import fun.steven.bookstore.pojo.dto.user.FindUserReponse;
import fun.steven.bookstore.pojo.dto.user.FindUsersResponse;

public interface IUserService {

    public boolean add(RegisterRequest request);

    public boolean delete(Long userId);

    public FindUserReponse findUser(Long userId);

    public FindUsersResponse findAllUsers();

    public boolean update(UpdateRequest request);

    public boolean banUser(String username);

    public boolean unbanUser(String username);

    public SessionDto login(LoginRequest request);
}
