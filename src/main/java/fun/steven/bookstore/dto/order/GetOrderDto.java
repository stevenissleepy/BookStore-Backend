package fun.steven.bookstore.dto.order;

import java.util.List;

import fun.steven.bookstore.dto.address.AddressDto;
import fun.steven.bookstore.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderDto {
    private AddressDto address;
    private List<GetOrderItemDto> orderItems;
    private Double totalPrice; 
    private String date;

    public GetOrderDto(Order order) {
        this.address = new AddressDto(order.getAddress());
        this.orderItems = order.getOrderItems().stream().map(GetOrderItemDto::new).toList();
        this.totalPrice = order.getTotalPrice();
        this.date = order.getDate().toString().split("T")[0];
    }
}
