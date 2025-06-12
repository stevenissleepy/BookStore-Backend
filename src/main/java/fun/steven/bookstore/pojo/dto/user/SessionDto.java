package fun.steven.bookstore.pojo.dto.user;

import fun.steven.bookstore.pojo.entity.User;
import lombok.Data;

@Data
public class SessionDto {
    Long userId;
    String username;

    public SessionDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
    }
}
