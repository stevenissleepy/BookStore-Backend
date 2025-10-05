package fun.steven.bookstore.pojo.dto.order;

import java.util.List;

import org.springframework.data.domain.Page;

import fun.steven.bookstore.pojo.entity.Order;
import lombok.Data;

@Data
public class FindOrdersResponse {
    Integer quantity;
    private List<FindOrderResponse> orders;
    private Integer currentPage;
    private Boolean first;
    private Boolean last;

    public FindOrdersResponse(Page<Order> orders) {
        this.quantity = (int) orders.getTotalElements();
        this.orders = orders.stream().map(FindOrderResponse::new).toList();
        this.currentPage = orders.getNumber();
        this.first = orders.isFirst();
        this.last = orders.isLast();
    }
}