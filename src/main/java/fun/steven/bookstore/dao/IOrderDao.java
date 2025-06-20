package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.order.AddOrderDto;
import fun.steven.bookstore.pojo.dto.order.GetOrdersDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;
import fun.steven.bookstore.pojo.dto.stats.ResultDto;
import fun.steven.bookstore.pojo.dto.stats.DateRangeDto;

public interface IOrderDao {
    boolean createOrder(Long cartId, AddOrderDto orderDto);

    GetOrdersDto searchUserOrders(Long userId, SearchDto searchDto);
    GetOrdersDto searchAllOrders(SearchDto searchDto);
    ResultDto<BookDto> statsBooks(Long userId, DateRangeDto dateRangeDto);
    ResultDto<String> searchTop10Books(DateRangeDto dateRangeDto);
    ResultDto<String> searchTop10Users(DateRangeDto dateRangeDto);
}
