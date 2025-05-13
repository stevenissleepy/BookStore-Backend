package fun.steven.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.dto.order.AddOrderDto;
import fun.steven.bookstore.dto.order.GetOrdersDto;
import fun.steven.bookstore.service.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping
    public ResponseMessage<String> createOrder(@RequestBody AddOrderDto addOrderDto) {
        orderService.cartToOrder(addOrderDto);
        return ResponseMessage.success("create order success", null);
    }

    @GetMapping("/{userId}")
    public ResponseMessage<GetOrdersDto> getOrdersByUserId(@PathVariable Long userId) {
        GetOrdersDto getOrdersDto = orderService.getUserOrders(userId);
        return ResponseMessage.success("get orders success", getOrdersDto);
    }
}
