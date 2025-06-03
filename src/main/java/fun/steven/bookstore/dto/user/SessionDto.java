package fun.steven.bookstore.dto.user;

import fun.steven.bookstore.entity.User;
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
