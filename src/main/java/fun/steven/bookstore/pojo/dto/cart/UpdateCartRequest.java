package fun.steven.bookstore.pojo.dto.cart;

import lombok.Data;

@Data
public class UpdateCartRequest {
    private Long userId;
    private Long bookId; 
    private Integer quantity;
}
