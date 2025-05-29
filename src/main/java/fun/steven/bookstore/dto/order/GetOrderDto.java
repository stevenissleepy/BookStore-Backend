package fun.steven.bookstore.dto.order;

import java.util.List;

import fun.steven.bookstore.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderDto {
    private String address;
    private String tel;
    private String receiver;
    private Integer totalPrice; 
    private List<GetOrderItemDto> orderItems;
    private String date;

    public GetOrderDto(Order order) {
        this.address = order.getAddress();
        this.tel = order.getTel();
        this.receiver = order.getReceiver();
        this.totalPrice = order.getTotalPrice();
        this.orderItems = order.getOrderItems().stream().map(GetOrderItemDto::new).toList();
        this.date = order.getDate().toString().split("T")[0];
    }
}
