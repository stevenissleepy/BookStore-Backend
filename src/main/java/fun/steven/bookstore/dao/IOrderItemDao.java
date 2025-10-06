package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.pojo.entity.OrderItem;

public interface IOrderItemDao {
    boolean saveAll(List<OrderItem> orderItems);
}
