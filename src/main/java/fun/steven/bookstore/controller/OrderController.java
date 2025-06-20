package fun.steven.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.dto.ResponseMessage;
import fun.steven.bookstore.pojo.dto.order.createOrderRequestDto;
import fun.steven.bookstore.pojo.dto.order.OrdersResponseDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;
import fun.steven.bookstore.service.IOrderService;
import fun.steven.bookstore.utils.annotation.AdminOnly;
import fun.steven.bookstore.utils.annotation.CurrentUserId;
import fun.steven.bookstore.utils.annotation.UserOnly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @UserOnly
    @PostMapping
    public ResponseMessage<String> createOrder(@RequestBody createOrderRequestDto addOrderDto, @CurrentUserId Long userId) {
        orderService.cartToOrder(userId, addOrderDto);
        return ResponseMessage.success("create order success", null);
    }

    @UserOnly
    @PostMapping("/search")
    public ResponseMessage<OrdersResponseDto> searchUserOrders(
            @CurrentUserId Long userId,
            @RequestBody SearchDto searchDto) {
        OrdersResponseDto getOrdersDto = orderService.searchUserOrders(userId, searchDto);
        return ResponseMessage.success("search user orders success", getOrdersDto);
    }

    @AdminOnly
    @PostMapping("/search/all")
    public ResponseMessage<OrdersResponseDto> searchAllOrders(@RequestBody SearchDto searchDto) {
        OrdersResponseDto getOrdersDto = orderService.searchAllOrders(searchDto);
        return ResponseMessage.success("search all orders success", getOrdersDto);

    }
}
