package fun.steven.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dao.IOrderDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.CartItem;
import fun.steven.bookstore.entity.Order;
import fun.steven.bookstore.entity.OrderItem;
import fun.steven.bookstore.entity.User;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IOrderDao orderDao;

    @Override
    public boolean cartToOrder(Long userId) {
        User user = userDao.getById(userId);
        Cart cart = user.getCart();
        List<CartItem> cartItems = cartDao.getCartItems(cart);

        /* 创建订单 */
        Order order = new Order();
        order.setUser(user);
        orderDao.add(order);

        /* 将购物车中的商品加到订单中 */
        List<OrderItem> orderItems = cartItems.stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(item.getBook());
            orderItem.setQuantity(item.getQuantity());
            orderDao.addOrderItem(orderItem);
            return orderItem;
        }).toList();
        order.setOrderItems(orderItems);

        /* 清空购物车 */
        cartDao.clear(cart);

        return true;
    }
}
