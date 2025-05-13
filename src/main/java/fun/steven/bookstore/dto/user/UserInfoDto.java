package fun.steven.bookstore.dto.user;

import lombok.Data;

@Data
public class UserInfoDto {
    private Long id;
    private String userName;
    private String avatar;
    private Double balance;
}
