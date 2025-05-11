package fun.steven.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.service.ICartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    ICartService cartService;

    @PostMapping("/{userId}")
    public ResponseMessage<String> createOrder(@PathVariable Long userId) {
        cartService.cartToOrder(userId);
        return ResponseMessage.success("create order success", null);
    }
}
