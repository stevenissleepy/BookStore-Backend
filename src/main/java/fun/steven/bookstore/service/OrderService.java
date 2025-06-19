package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dao.IOrderDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.pojo.dto.order.AddOrderDto;
import fun.steven.bookstore.pojo.dto.order.GetOrdersDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;
import fun.steven.bookstore.pojo.dto.stats.SalesDto;
import fun.steven.bookstore.pojo.dto.stats.SearchSalesDto;

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
        
        Long cartId = userDao.getCartId(userId);
        for(Long bookId : addOrderDto.getBookIds()) {
            cartDao.deleteFromCart(cartId, bookId);
        }

        return true;
    }

    @Override
    public GetOrdersDto getUserOrders(Long userId) {
        return orderDao.getOrders(userId);
    }

    @Override
    public GetOrdersDto searchAllOrders(SearchDto searchDto) {
        return orderDao.searchAllOrders(searchDto);
    }
    
    @Override
    public SalesDto searchTop10Books(SearchSalesDto searchSalesDto) {
        return orderDao.searchTop10Books(searchSalesDto);
    }

    @Override
    public SalesDto searchTop10Users(SearchSalesDto searchSalesDto) {
        return orderDao.searchTop10Users(searchSalesDto);
    }
}
