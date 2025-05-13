package fun.steven.bookstore.dto.order;

import lombok.Data;

@Data
public class AddOrderDto {
    private Long userId;
    private Long addressId;
}
