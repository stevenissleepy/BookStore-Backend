package fun.steven.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.annotation.CurrentUserId;
import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.dto.user.LoginDto;
import fun.steven.bookstore.dto.user.UpdateUserDto;
import fun.steven.bookstore.dto.user.UserDto;
import fun.steven.bookstore.dto.user.UserInfoDto;
import fun.steven.bookstore.service.IUserService;


@RestController                         /* 将接口方法返回的对象自动转化成 json */
@RequestMapping("/user")                /* 设置请求路径为 /user */
public class UserController {

    @Autowired
    private IUserService userService;   /* 注入用户服务 */

    /**
     * @brief 增加用户
     * @note 该方法使用 POST 请求，路径为 /user
     * 
     * @param userDto UserDto
     * @return ResponseMessage<User> 返回响应消息对象
     */
    @PostMapping("/register")
    public ResponseMessage<UserInfoDto> add(@RequestBody UserDto userDto) {
        UserInfoDto userInfoDto = userService.add(userDto);

        return ResponseMessage.success("add user success!", userInfoDto);
    }

    /**
     * @brief 更新用户
     * @note 该方法使用 POST 请求，路径为 /user
     * 
     * @param userDto UpdateUserDto
     * @return ResponseMessage<User> 返回响应消息对象
     */
    @PutMapping
    public ResponseMessage<UserInfoDto> update(@RequestBody UpdateUserDto userDto, @CurrentUserId Long userId) {
        UserInfoDto userInfoDto = userService.update(userId, userDto);

        return ResponseMessage.success("update user success!", userInfoDto);
    }

    /**
     * @brief 查询用户
     * @note 该方法使用 GET 请求，路径为 /user
     * 
     * @param userId 用户 ID
     * @return ResponseMessage<User> 返回响应消息对象
     */
    @GetMapping
    public ResponseMessage<UserInfoDto> query(@CurrentUserId Long userId) {
        UserInfoDto userInfoDto = userService.query(userId);

        return ResponseMessage.success("query user success!", userInfoDto);
    }

    /**
     * @brief 登录
     * @note 该方法使用 POST 请求，路径为 /user/login
     * 
     * @param username 用户名
     * @param password 密码
     * @param request HttpServletRequest
     * @return ResponseMessage<String> 返回响应消息对象
     */
    @PostMapping("/login")
    public ResponseMessage<String> login(@RequestBody LoginDto loginDto,  HttpServletRequest request) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        if (username == null || password == null) {
            return ResponseMessage.error(401, "用户名或密码不能为空");
        }
        
        Long userId = userService.login(loginDto);
        request.getSession().setAttribute("userId", userId);
        return ResponseMessage.success("login success!", null);
    }

    /**
     * @brief 登出
     * @note 该方法使用 GET 请求，路径为 /user/logout/{userId}
     * 
     * @param request HttpServletRequest
     * @return ResponseMessage<String> 返回响应消息对象
     */
    @PostMapping("/logout")
    public ResponseMessage<String> logout(HttpServletRequest request, @CurrentUserId Long userId) {
        Long sessionUserId = (Long) request.getSession().getAttribute("userId");
        if (sessionUserId == null || !sessionUserId.equals(userId)) {
            return ResponseMessage.error(403, "用户未登录或登录已过期");
        }
        request.getSession().invalidate();
        return ResponseMessage.success("logout success!", null);
    }
}
