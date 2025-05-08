package fun.steven.bookstore.entity.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String userName;
    private String password;
}
