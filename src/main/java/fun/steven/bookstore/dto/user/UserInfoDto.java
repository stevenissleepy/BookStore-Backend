package fun.steven.bookstore.dto.user;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.entity.User;
import lombok.Data;

@Data
public class UserInfoDto {
    private Long id;
    private String userName;
    private String avatar;
    private Double balance;

    public UserInfoDto(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
