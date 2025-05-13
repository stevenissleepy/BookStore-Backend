package fun.steven.bookstore.dto.order;

import java.util.List;

import fun.steven.bookstore.dto.address.AddressDto;
import lombok.Data;

@Data
public class GetOrderDto {
    private AddressDto address;
    private List<GetOrderItemDto> orderItems;
}
