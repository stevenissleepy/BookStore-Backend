package fun.steven.bookstore.pojo.dto.cart;

import lombok.Data;

@Data
public class AddToCartRequestDto {
    private Long bookId; 
    private Integer quantity;
}
