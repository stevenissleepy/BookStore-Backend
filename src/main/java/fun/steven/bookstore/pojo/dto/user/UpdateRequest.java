package fun.steven.bookstore.pojo.dto.user;

import lombok.Data;

@Data
public class UpdateRequest {
    private Long id;
    private String username;
    private String password;
    private String avatar;
}
