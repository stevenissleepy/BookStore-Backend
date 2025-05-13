package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.entity.Order;
import fun.steven.bookstore.entity.OrderItem;

public interface IOrderDao {
    boolean add(Order order);
    boolean addOrderItem(OrderItem orderItem);

    List<Order> getUserOrders(Long userId);
}
