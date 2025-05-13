package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.order.AddOrderDto;

public interface IOrderService {
    boolean cartToOrder(AddOrderDto addOrderDto);
}
