package fun.steven.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.dto.order.CreateOrderRequest;
import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.dto.order.FindOrdersResponse;
import fun.steven.bookstore.pojo.dto.order.SearchAllOrdersRequest;
import fun.steven.bookstore.pojo.dto.order.SearchUserOrdersRequest;
import fun.steven.bookstore.service.IOrderService;
import fun.steven.bookstore.utils.annotation.AdminOnly;
import fun.steven.bookstore.utils.annotation.CurrentUserId;
import fun.steven.bookstore.utils.annotation.UserOnly;
import fun.steven.bookstore.utils.kafka.KafkaTopicConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private KafkaTemplate<String, CreateOrderRequest> createOrderTemplate;

    @UserOnly
    @PostMapping
    public ResponseMessage<?> createOrder(@CurrentUserId Long userId, @RequestBody CreateOrderRequest request) {
        request.setUserId(userId);
        createOrderTemplate.send(KafkaTopicConfig.NEW_ORDER_TOPIC, request);
        return ResponseMessage.success("订单请求已提交，正在处理中...", null);
    }

    @UserOnly
    @PostMapping("/search")
    public ResponseMessage<FindOrdersResponse> searchUserOrders(
            @CurrentUserId Long userId,
            @RequestBody SearchUserOrdersRequest request) {
        request.setUserId(userId);
        FindOrdersResponse response = orderService.searchUserOrders(request);
        return ResponseMessage.success("search user orders success", response);
    }

    @AdminOnly
    @PostMapping("/search/all")
    public ResponseMessage<FindOrdersResponse> searchAllOrders(@RequestBody SearchAllOrdersRequest request) {
        FindOrdersResponse response = orderService.searchAllOrders(request);
        return ResponseMessage.success("search all orders success", response);
    }
}
