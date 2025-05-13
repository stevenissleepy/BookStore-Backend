package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.dto.AddCartItemDto;
import fun.steven.bookstore.dto.GetCartDto;
import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.service.ICartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping
    public ResponseMessage<String> addToCart(@RequestBody AddCartItemDto cartItemDto) {
        cartService.addToCart(cartItemDto);
        return ResponseMessage.success("Item added to cart successfully", null);
    }

    @GetMapping("/{userId}")
    public ResponseMessage<GetCartDto> getCart(@PathVariable Long userId) {
        GetCartDto cart = cartService.getCart(userId);
        return ResponseMessage.success("Cart retrieved successfully", cart);
    }
}
