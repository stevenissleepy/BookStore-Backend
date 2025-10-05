package fun.steven.bookstore.pojo.dto.user;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
