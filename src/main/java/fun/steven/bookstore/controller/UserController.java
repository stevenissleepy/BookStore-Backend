package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.dto.UserDto;
import fun.steven.bookstore.entity.User;
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
        return ResponseMessage.success("add user success!", user);
    }

    /**
     * @brief 删除用户
     * @note 该方法使用 DELETE 请求，路径为 /user
     * 
     * @param userDto 用户数据传输对象，至少包含用户 ID
     * @return ResponseMessage<User> 返回响应消息对象
     */
    @DeleteMapping()
    public ResponseMessage<User> delete(@RequestBody UserDto userDto) {
        User user = userService.delete(userDto.getUserId());
        return ResponseMessage.success("del user success!", user);
    }

    /**
     * @brief 更新用户
     * @note 该方法使用 POST 请求，路径为 /user
     * 
     * @param userDto 用户数据传输对象
     * @return ResponseMessage<User> 返回响应消息对象
     */
    @PutMapping
    public ResponseMessage<User> update(@RequestBody UserDto userDto) {
        User user = userService.update(userDto);
        return ResponseMessage.success("update user success!", user);
    }

    /**
     * @brief 查询用户
     * @note 该方法使用 GET 请求，路径为 /user/{userId}
     * 
     * @param userId 用户 ID，至少包含用户 ID
     * @return ResponseMessage<User> 返回响应消息对象
     */
    @GetMapping
    public ResponseMessage<User> query(@RequestBody UserDto userDto) {
        User user = userService.query(userDto.getUserId());
        return ResponseMessage.success("query user success!", user);
    }
}
