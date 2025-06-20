package fun.steven.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.dto.ResponseMessage;
import fun.steven.bookstore.pojo.dto.order.AddOrderDto;
import fun.steven.bookstore.pojo.dto.order.GetOrdersDto;
import fun.steven.bookstore.pojo.dto.order.SearchDto;
import fun.steven.bookstore.service.IOrderService;
import fun.steven.bookstore.utils.annotation.AdminOnly;
import fun.steven.bookstore.utils.annotation.CurrentUserId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping
    public ResponseMessage<String> createOrder(@RequestBody AddOrderDto addOrderDto, @CurrentUserId Long userId) {
        orderService.cartToOrder(userId, addOrderDto);
        return ResponseMessage.success("create order success", null);
    }

    @PostMapping("/search")
    public ResponseMessage<GetOrdersDto> searchUserOrders(
            @CurrentUserId Long userId,
            @RequestBody SearchDto searchDto) {
        GetOrdersDto getOrdersDto = orderService.searchUserOrders(userId, searchDto);
        return ResponseMessage.success("search user orders success", getOrdersDto);
    }

    @AdminOnly
    @PostMapping("/search/all")
    public ResponseMessage<GetOrdersDto> searchAllOrders(@RequestBody SearchDto searchDto) {
        GetOrdersDto getOrdersDto = orderService.searchAllOrders(searchDto);
        return ResponseMessage.success("search all orders success", getOrdersDto);

    }
}
