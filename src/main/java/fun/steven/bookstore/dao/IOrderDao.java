package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.dto.order.AddOrderDto;
import fun.steven.bookstore.pojo.dto.order.GetOrdersDto;

public interface IOrderDao {
    boolean createOrder(Long cartId, AddOrderDto orderDto);

    GetOrdersDto getOrders(Long userId);
}
