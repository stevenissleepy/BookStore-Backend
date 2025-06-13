package fun.steven.bookstore.pojo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionDto {
    Long userId;
    String username;
}
