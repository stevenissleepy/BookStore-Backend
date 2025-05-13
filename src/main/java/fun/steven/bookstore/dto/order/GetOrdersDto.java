package fun.steven.bookstore.dto.order;

import java.util.List;

import fun.steven.bookstore.entity.Order;
import lombok.Data;

@Data
public class GetOrdersDto {
    private List<GetOrderDto> orders;

    public GetOrdersDto(List<Order> orders) {
        this.orders = orders.stream().map(GetOrderDto::new).toList();
    }
}
