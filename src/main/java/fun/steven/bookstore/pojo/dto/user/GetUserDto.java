package fun.steven.bookstore.pojo.dto.user;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.pojo.entity.User;
import lombok.Data;

@Data
public class GetUserDto {
    private String username;
    private String avatar;
    private Integer balance;
    private String state;

    public GetUserDto(User user) {
        BeanUtils.copyProperties(user, this);
        this.state = user.getUserAuth().getState();
    }
}
