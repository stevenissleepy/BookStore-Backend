package fun.steven.bookstore.dto.user;

import lombok.Data;

@Data
public class UpdateUserDto {
    private Long userId;
    private String userName;
    private String password;
}
