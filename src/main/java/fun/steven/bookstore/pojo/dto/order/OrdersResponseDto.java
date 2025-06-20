package fun.steven.bookstore.pojo.dto.order;

import java.util.List;

import org.springframework.data.domain.Page;

import fun.steven.bookstore.pojo.entity.Order;
import lombok.Data;

@Data
public class OrdersResponseDto {
    Integer quantity;
    private List<OrderResponseDto> orders;
    private Integer currentPage;
    private Boolean first;
    private Boolean last;

    public OrdersResponseDto(Page<Order> orders) {
        this.quantity = (int) orders.getTotalElements();
        this.orders = orders.stream().map(OrderResponseDto::new).toList();
        this.currentPage = orders.getNumber();
        this.first = orders.isFirst();
        this.last = orders.isLast();
    }
}