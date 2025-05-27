package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dao.IOrderDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.dto.order.AddOrderDto;
import fun.steven.bookstore.dto.order.GetOrdersDto;

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
        return orderDao.getOrders(userId);
    }
}
