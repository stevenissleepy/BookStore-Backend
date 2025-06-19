package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.book.SalesDto;
import fun.steven.bookstore.pojo.dto.book.SearchSalesDto;
import fun.steven.bookstore.pojo.dto.order.AddOrderDto;
import fun.steven.bookstore.pojo.dto.order.GetOrdersDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;

public interface IOrderService {
    boolean cartToOrder(Long userId, AddOrderDto addOrderDto);
    GetOrdersDto getUserOrders(Long userId);
    GetOrdersDto searchAllOrders(SearchDto searchDto);
    SalesDto searchTop10Books(SearchSalesDto searchSalesDto);
}
