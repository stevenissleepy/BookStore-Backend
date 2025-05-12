package fun.steven.bookstore.dto;

import lombok.Data;

@Data
public class ResponseUserDto {
    private Long id;
    private String userName;
    private String avatar;
    private Double balance;
    private Long cartId;
}
