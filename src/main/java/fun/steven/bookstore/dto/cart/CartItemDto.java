package fun.steven.bookstore.dto.cart;

import lombok.Data;

@Data
public class CartItemDto {
    private Long bookId; 
    private Integer quantity;
}
