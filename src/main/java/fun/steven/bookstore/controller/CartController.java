package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.dto.CartItemDto;
import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.service.ICartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping
    public ResponseMessage<String> addToCart(CartItemDto cartItemDto) {
        cartService.addToCart(cartItemDto);
        return ResponseMessage.success("Item added to cart successfully", null);
    }
}
