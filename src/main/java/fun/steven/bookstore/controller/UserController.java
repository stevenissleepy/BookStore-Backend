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

import fun.steven.bookstore.pojo.dto.ResponseMessage;
import fun.steven.bookstore.pojo.dto.user.LoginDto;
import fun.steven.bookstore.pojo.dto.user.RegisterDto;
import fun.steven.bookstore.pojo.dto.user.SessionDto;
import fun.steven.bookstore.pojo.dto.user.UpdateUserDto;
import fun.steven.bookstore.pojo.dto.user.GetUserDto;
import fun.steven.bookstore.pojo.dto.user.GetUsersDto;
import fun.steven.bookstore.service.IUserService;
import fun.steven.bookstore.utils.annotation.AdminOnly;
import fun.steven.bookstore.utils.annotation.CurrentUserId;


@RestController                         /* 将接口方法返回的对象自动转化成 json */
@RequestMapping("/user")                /* 设置请求路径为 /user */
public class UserController {

    @Autowired
    private IUserService userService;   /* 注入用户服务 */

    @PostMapping("/register")
    public ResponseMessage<String> add(@RequestBody RegisterDto userDto) {
        userService.add(userDto);

        return ResponseMessage.success("注册成功", null);
    }

    @PutMapping
    public ResponseMessage<GetUserDto> update(@CurrentUserId Long userId, @RequestBody UpdateUserDto userDto) {
        GetUserDto userInfoDto = userService.update(userId, userDto);

        return ResponseMessage.success("update user success!", userInfoDto);
    }

    @GetMapping
    public ResponseMessage<GetUserDto> get(@CurrentUserId Long userId) {
        GetUserDto userInfoDto = userService.get(userId);

        return ResponseMessage.success("query user success!", userInfoDto);
    }

    @PostMapping("/login")
    public ResponseMessage<String> login(@RequestBody LoginDto loginDto,  HttpServletRequest request) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        if (username == null || password == null) {
            return ResponseMessage.error(401, "用户名或密码不能为空");
        }
        
        SessionDto userSession = userService.login(loginDto);
        request.getSession().setAttribute("userId", userSession.getUserId());
        request.getSession().setAttribute("username", userSession.getUsername());
        return ResponseMessage.success("login success!", null);
    }

    @PostMapping("/logout")
    public ResponseMessage<String> logout(HttpServletRequest request, @CurrentUserId Long userId) {
        Long sessionUserId = (Long) request.getSession().getAttribute("userId");
        if (sessionUserId == null || !sessionUserId.equals(userId)) {
            return ResponseMessage.error(403, "用户未登录或登录已过期");
        }
        request.getSession().invalidate();
        return ResponseMessage.success("logout success!", null);
    }
    
    @AdminOnly
    @PostMapping("/all")
    public ResponseMessage<GetUsersDto> getAllUsers() {
        GetUsersDto users = userService.getAll();
        return ResponseMessage.success("get all users success!", users);
    }

    @AdminOnly
    @PutMapping("/ban/{username}")
    public ResponseMessage<String> banUser(@PathVariable String username) {
        userService.banUser(username);
        return ResponseMessage.success("ban user success!", null);
    }

    @AdminOnly
    @PutMapping("/unban/{username}")
    public ResponseMessage<String> unbanUser(@PathVariable String username) {
        userService.unbanUser(username);
        return ResponseMessage.success("unban user success!", null);
    }
}
