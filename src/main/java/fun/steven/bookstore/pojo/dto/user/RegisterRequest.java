package fun.steven.bookstore.pojo.dto.user;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}