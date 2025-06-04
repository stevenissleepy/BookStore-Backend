package fun.steven.bookstore.dto.user;

import lombok.Data;

@Data
public class UpdateUserDto {
    private String username;
    private String avatar;
    private String password;
}
