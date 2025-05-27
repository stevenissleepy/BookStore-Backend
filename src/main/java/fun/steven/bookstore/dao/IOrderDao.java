package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.dto.order.AddOrderDto;
import fun.steven.bookstore.entity.Order;

public interface IOrderDao {
    boolean createOrder(Long cartId, AddOrderDto orderDto);

    List<Order> getUserOrders(Long userId);
}
