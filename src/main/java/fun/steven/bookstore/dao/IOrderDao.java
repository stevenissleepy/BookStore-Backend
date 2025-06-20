package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.order.createOrderRequestDto;
import fun.steven.bookstore.pojo.dto.order.OrdersResponseDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;
import fun.steven.bookstore.pojo.dto.stats.ResultDto;
import fun.steven.bookstore.pojo.dto.stats.DateRangeDto;

public interface IOrderDao {
    boolean createOrder(Long cartId, createOrderRequestDto orderDto);

    OrdersResponseDto searchUserOrders(Long userId, SearchDto searchDto);
    OrdersResponseDto searchAllOrders(SearchDto searchDto);
    ResultDto<BookDto> statsBooks(Long userId, DateRangeDto dateRangeDto);
    ResultDto<String> searchTop10Books(DateRangeDto dateRangeDto);
    ResultDto<String> searchTop10Users(DateRangeDto dateRangeDto);
}
