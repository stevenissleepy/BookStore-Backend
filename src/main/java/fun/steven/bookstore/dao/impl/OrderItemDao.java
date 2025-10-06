package fun.steven.bookstore.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fun.steven.bookstore.dao.IOrderItemDao;
import fun.steven.bookstore.pojo.entity.OrderItem;
import fun.steven.bookstore.repository.OrderItemRepository;

public class OrderItemDao implements IOrderItemDao{
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public boolean saveAll(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems) != null;
    }
}
