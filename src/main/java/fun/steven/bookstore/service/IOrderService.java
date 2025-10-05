package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.stats.ResultDto;
import fun.steven.bookstore.pojo.dto.stats.StatsAllRequest;
import fun.steven.bookstore.pojo.dto.stats.StatsUserRequest;
import fun.steven.bookstore.pojo.dto.book.FindBookResponse;
import fun.steven.bookstore.pojo.dto.order.CreateOrderRequest;
import fun.steven.bookstore.pojo.dto.order.FindOrdersResponse;
import fun.steven.bookstore.pojo.dto.order.SearchUserOrdersRequest;
import fun.steven.bookstore.pojo.dto.order.SearchAllOrdersRequest;

public interface IOrderService {
    boolean createOrder(CreateOrderRequest request);

    FindOrdersResponse searchUserOrders(SearchUserOrdersRequest request);
    FindOrdersResponse searchAllOrders(SearchAllOrdersRequest request);
    ResultDto<FindBookResponse> statsBooks(StatsUserRequest request);
    ResultDto<String> searchTop10Books(StatsAllRequest request);
    ResultDto<String> searchTop10Users(StatsAllRequest request);
}
