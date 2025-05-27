package fun.steven.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dao.IOrderDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.dto.order.AddOrderDto;
import fun.steven.bookstore.dto.order.GetOrdersDto;
import fun.steven.bookstore.entity.Order;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IOrderDao orderDao;

    @Override
    public boolean cartToOrder(Long userId, AddOrderDto addOrderDto) {
        orderDao.createOrder(userId, addOrderDto);
        cartDao.clear(userDao.getCartId(userId));
        
        return true;
    }

    @Override
    public GetOrdersDto getUserOrders(Long userId) {
        List<Order> orders = orderDao.getUserOrders(userId);
        return new GetOrdersDto(orders);
    }
}
