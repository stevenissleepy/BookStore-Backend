package fun.steven.bookstore.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.dto.UserDto;

@RestController             /* 将接口方法返回的对象自动转化成 json */
@RequestMapping("/user")    /* 设置请求路径为 /user */
public class UserController {

    /**
     * @brief 增加用户
     * 
     * 
     */
    @PostMapping
    public ResponseMessage<UserDto> add(@RequestBody UserDto userDto) {
        return new ResponseMessage<UserDto>(200, "ok", userDto);
    }
}
