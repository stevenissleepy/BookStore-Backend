package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.entity.Order;

public interface IOrderDao {
    boolean add(Order order);

    List<Order> getUserOrders(Long userId);
}
