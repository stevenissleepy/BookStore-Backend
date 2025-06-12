package fun.steven.bookstore.pojo.dto.user;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String email;
}