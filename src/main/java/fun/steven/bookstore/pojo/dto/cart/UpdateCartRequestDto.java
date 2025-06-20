package fun.steven.bookstore.pojo.dto.cart;

import lombok.Data;

@Data
public class UpdateCartRequestDto {
    private Long bookId; 
    private Integer quantity;
}
