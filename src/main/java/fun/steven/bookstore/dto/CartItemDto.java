package fun.steven.bookstore.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long userId;
    private Long bookId; 
    private Integer quantity;
}
