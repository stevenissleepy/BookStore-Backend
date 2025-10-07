package fun.steven.bookstore.pojo.dto.user;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.pojo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindUserResponse {
    private Long id;
    private String username;
    private String email;
    private String avatar;
    private Integer balance;
    private String state;

    public FindUserResponse(User user) {
        BeanUtils.copyProperties(user, this);
        this.state = user.getUserAuth().getState();
    }
}
