package fun.steven.bookstore.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long cartId;
    private Long bookId; 
    private Integer quantity;
}
