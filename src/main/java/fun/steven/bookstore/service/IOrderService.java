package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.order.AddOrderDto;
import fun.steven.bookstore.pojo.dto.order.GetOrdersDto;

public interface IOrderService {
    boolean cartToOrder(Long userId, AddOrderDto addOrderDto);
    GetOrdersDto getUserOrders(Long userId);
}
