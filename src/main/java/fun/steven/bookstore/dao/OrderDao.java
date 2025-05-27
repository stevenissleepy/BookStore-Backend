package fun.steven.bookstore.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dto.order.AddOrderDto;
import fun.steven.bookstore.entity.CartItem;
import fun.steven.bookstore.entity.Order;
import fun.steven.bookstore.entity.OrderItem;
import fun.steven.bookstore.entity.User;
import fun.steven.bookstore.repository.OrderRepository;
import fun.steven.bookstore.repository.UserRepository;

@Repository
public class OrderDao implements IOrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createOrder(Long userId, AddOrderDto addOrderDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<CartItem> cartItems = user.getCart().getCartItems();

        /* 创建订单 */
        Order order = new Order();
        order.setUser(user);
        order.setReceiver(addOrderDto.getReceiver());
        order.setTel(addOrderDto.getTel());
        order.setAddress(addOrderDto.getAddress());
        order.setDate(java.time.LocalDateTime.now());

        /* 将购物车中的商品加到订单中 */
        List<OrderItem> orderItems = cartItems.stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(item.getBook());
            orderItem.setQuantity(item.getQuantity());
            return orderItem;
        }).toList();
        order.setOrderItems(orderItems);

        /* 计算总价格 */
        Double totalPrice = cartItems.stream().mapToDouble(
                item -> item.getBook().getPrice() * item.getQuantity()).sum();
        order.setTotalPrice(totalPrice);

        /* 保存订单 */
        user.getOrders().add(order);
        userRepository.save(user);

        return true;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId).orElse(new ArrayList<>());
    }
}
