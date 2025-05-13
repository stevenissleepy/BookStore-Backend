package fun.steven.bookstore.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.Order;
import fun.steven.bookstore.entity.OrderItem;
import fun.steven.bookstore.repository.OrderItemRepository;
import fun.steven.bookstore.repository.OrderRepository;

@Repository
public class OrderDao implements IOrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public boolean add(Order order) {
        orderRepository.save(order);
        return true;
    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
        return true;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId).orElse(new ArrayList<>());
    }
}
