package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

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
        return ResponseMessage.success("add user success!", user);
    }

    /**
     * @brief 删除用户
     * @note 该方法使用 DELETE 请求，路径为 /user
     * 
     * @param userDto 用户数据传输对象
     * @return ResponseMessage<User> 返回响应消息对象
     */
    @DeleteMapping("/{userId}")
    public ResponseMessage<User> delete(@PathVariable String userId) {
        User user = userService.delete(userId);
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
     * @param userId 用户 ID
     * @return ResponseMessage<User> 返回响应消息对象
     */
    @GetMapping("/{userId}")
    public ResponseMessage<User> query(@PathVariable String userId) {
        User user = userService.query(userId);
        return ResponseMessage.success("query user success!", user);
    }
}
