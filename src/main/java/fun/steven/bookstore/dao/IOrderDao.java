package fun.steven.bookstore.dao;

import fun.steven.bookstore.entity.Order;
import fun.steven.bookstore.entity.OrderItem;

public interface IOrderDao {
    boolean add(Order order);
    boolean addOrderItem(OrderItem orderItem);
}
