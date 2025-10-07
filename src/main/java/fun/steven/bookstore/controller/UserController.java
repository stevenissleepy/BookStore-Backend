package fun.steven.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.dto.user.FindUserResponse;
import fun.steven.bookstore.pojo.dto.user.FindUsersResponse;
import fun.steven.bookstore.pojo.dto.user.LoginRequest;
import fun.steven.bookstore.pojo.dto.user.LogoutResponse;
import fun.steven.bookstore.pojo.dto.user.RegisterRequest;
import fun.steven.bookstore.pojo.dto.user.SessionDto;
import fun.steven.bookstore.pojo.dto.user.UpdateRequest;
import fun.steven.bookstore.service.ISessionService;
import fun.steven.bookstore.service.IUserService;
import fun.steven.bookstore.utils.annotation.AdminOnly;
import fun.steven.bookstore.utils.annotation.CurrentUserId;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISessionService sessionService;

    @PostMapping("/register")
    public ResponseMessage<String> add(@RequestBody RegisterRequest request) {
        userService.add(request);

        return ResponseMessage.success("注册成功", null);
    }

    @PutMapping
    public ResponseMessage<?> update(@CurrentUserId Long userId, @RequestBody UpdateRequest request) {
        request.setId(userId);
        userService.update(request);

        return ResponseMessage.success("update user success!", null);
    }

    @GetMapping
    public ResponseMessage<FindUserResponse> findUser(@CurrentUserId Long userId) {
        FindUserResponse response = userService.findUser(userId);

        return ResponseMessage.success("query user success!", response);
    }

    @AdminOnly
    @PostMapping("/all")
    public ResponseMessage<FindUsersResponse> findAllUsers() {
        FindUsersResponse response = userService.findAllUsers();
        return ResponseMessage.success("get all users success!", response);
    }

    @PostMapping("/login")
    public ResponseMessage<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        SessionDto sessionInfo = userService.login(loginRequest);
        sessionService.startTimer();

        request.getSession().setAttribute("userId", sessionInfo.getUserId());
        request.getSession().setAttribute("username", sessionInfo.getUsername());
        return ResponseMessage.success("login success!", null);
    }

    @PostMapping("/logout")
    public ResponseMessage<LogoutResponse> logout(@CurrentUserId Long userId, HttpServletRequest request) {
        // 检查用户是否登录
        Long sessionUserId = (Long) request.getSession().getAttribute("userId");
        if (sessionUserId == null || !sessionUserId.equals(userId)) {
            return ResponseMessage.error(403, "用户未登录或登录已过期");
        }

        // 关闭 Session
        Long duration = sessionService.stopTimer();
        LogoutResponse response = new LogoutResponse(duration);
        request.getSession().invalidate();
        
        return ResponseMessage.success("logout success!", response);
    }

    @AdminOnly
    @PutMapping("/ban/{username}")
    public ResponseMessage<?> banUser(@PathVariable String username) {
        userService.banUser(username);
        return ResponseMessage.success("ban user success!", null);
    }

    @AdminOnly
    @PutMapping("/unban/{username}")
    public ResponseMessage<?> unbanUser(@PathVariable String username) {
        userService.unbanUser(username);
        return ResponseMessage.success("unban user success!", null);
    }
}
