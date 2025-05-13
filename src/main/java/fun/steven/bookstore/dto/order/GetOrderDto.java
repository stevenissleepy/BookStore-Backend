package fun.steven.bookstore.dto.order;

import java.util.List;

import fun.steven.bookstore.dto.address.GetAddressDto;
import fun.steven.bookstore.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderDto {
    private GetAddressDto address;
    private List<GetOrderItemDto> orderItems;

    public GetOrderDto(Order order) {
        this.address = new GetAddressDto(order.getAddress());
        this.orderItems = order.getOrderItems().stream().map(GetOrderItemDto::new).toList();
    }
}
