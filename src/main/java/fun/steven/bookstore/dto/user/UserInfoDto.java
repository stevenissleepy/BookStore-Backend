package fun.steven.bookstore.dto.user;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.entity.User;
import lombok.Data;

@Data
public class UserInfoDto {
    private String username;
    private String avatar;
    private Integer balance;
    private String role;

    public UserInfoDto(User user) {
        BeanUtils.copyProperties(user, this);
        this.role = user.getUserAuth().getRole();
    }
}
