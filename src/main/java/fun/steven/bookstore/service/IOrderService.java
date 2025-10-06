package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.order.CreateOrderRequest;
import fun.steven.bookstore.pojo.dto.order.FindOrdersResponse;
import fun.steven.bookstore.pojo.dto.order.SearchUserOrdersRequest;
import fun.steven.bookstore.pojo.dto.order.SearchAllOrdersRequest;

public interface IOrderService {
    boolean createOrder(CreateOrderRequest request);

    FindOrdersResponse searchUserOrders(SearchUserOrdersRequest request);
    FindOrdersResponse searchAllOrders(SearchAllOrdersRequest request);
}
