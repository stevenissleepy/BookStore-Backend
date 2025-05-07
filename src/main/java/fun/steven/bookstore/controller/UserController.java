package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.User;
import fun.steven.bookstore.pojo.dto.UserDto;
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
     * @param userDto 用户数据传输对象
     * @return ResponseMessage<User> 返回响应消息对象
     */
    @PostMapping
    public ResponseMessage<User> add(@RequestBody UserDto userDto) {
        User user = userService.add(userDto);
        return ResponseMessage.success(user);
    }
}
