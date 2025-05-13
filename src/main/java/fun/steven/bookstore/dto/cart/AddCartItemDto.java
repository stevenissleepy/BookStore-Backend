package fun.steven.bookstore.dto.cart;

import lombok.Data;

@Data
public class AddCartItemDto {
    private Long bookId; 
    private Integer quantity;
}
