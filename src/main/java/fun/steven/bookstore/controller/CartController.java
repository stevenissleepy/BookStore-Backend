package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.dto.cart.AddCartItemDto;
import fun.steven.bookstore.dto.cart.GetCartDto;
import fun.steven.bookstore.service.ICartService;
import fun.steven.bookstore.utils.annotation.CurrentUserId;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping
    public ResponseMessage<String> addToCart(@RequestBody AddCartItemDto cartItemDto, @CurrentUserId Long userId) {
        cartService.addToCart(userId, cartItemDto);
        return ResponseMessage.success("Item added to cart successfully", null);
    }

    @GetMapping
    public ResponseMessage<GetCartDto> getCart(@CurrentUserId Long userId) {
        GetCartDto cart = cartService.getCart(userId);
        return ResponseMessage.success("Cart retrieved successfully", cart);
    }
}
