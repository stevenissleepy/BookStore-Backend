package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dao.IOrderDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.order.AddOrderDto;
import fun.steven.bookstore.pojo.dto.order.GetOrdersDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;
import fun.steven.bookstore.pojo.dto.stats.ResultDto;
import fun.steven.bookstore.pojo.dto.stats.DateRangeDto;

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
    public GetOrdersDto searchUserOrders(Long userId, SearchDto searchDto) {
        return orderDao.searchUserOrders(userId, searchDto);
    }

    @Override
    public GetOrdersDto searchAllOrders(SearchDto searchDto) {
        return orderDao.searchAllOrders(searchDto);
    }
    
    @Override
    public ResultDto<BookDto> statsBooks(Long userId, DateRangeDto dateRangeDto) {
        return orderDao.statsBooks(userId, dateRangeDto);
    }

    @Override
    public ResultDto<String> searchTop10Books(DateRangeDto dateRangeDto) {
        return orderDao.searchTop10Books(dateRangeDto);
    }

    @Override
    public ResultDto<String> searchTop10Users(DateRangeDto dateRangeDto) {
        return orderDao.searchTop10Users(dateRangeDto);
    }
}
