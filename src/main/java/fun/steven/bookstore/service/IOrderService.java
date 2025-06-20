package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.stats.ResultDto;
import fun.steven.bookstore.pojo.dto.stats.DateRangeDto;
import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.dto.order.AddOrderDto;
import fun.steven.bookstore.pojo.dto.order.GetOrdersDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;

public interface IOrderService {
    boolean cartToOrder(Long userId, AddOrderDto addOrderDto);
    GetOrdersDto searchUserOrders(Long userId, SearchDto searchDto);
    GetOrdersDto searchAllOrders(SearchDto searchDto);
    ResultDto<BookDto> statsBooks(Long userId, DateRangeDto dateRangeDto);
    ResultDto<String> searchTop10Books(DateRangeDto dateRangeDto);
    ResultDto<String> searchTop10Users(DateRangeDto dateRangeDto);
}
