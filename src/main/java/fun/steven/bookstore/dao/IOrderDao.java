package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.dto.book.SalesDto;
import fun.steven.bookstore.pojo.dto.book.SearchSalesDto;
import fun.steven.bookstore.pojo.dto.order.AddOrderDto;
import fun.steven.bookstore.pojo.dto.order.GetOrdersDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;

public interface IOrderDao {
    boolean createOrder(Long cartId, AddOrderDto orderDto);

    GetOrdersDto getOrders(Long userId);
    GetOrdersDto searchAllOrders(SearchDto searchDto);
    SalesDto searchTop10Books(SearchSalesDto searchSalesDto);
}
