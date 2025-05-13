package fun.steven.bookstore.dto;

import lombok.Data;

@Data
public class AddCartItemDto {
    private Long userId;
    private Long bookId; 
    private Integer quantity;
}
