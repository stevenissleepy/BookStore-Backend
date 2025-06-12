package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.dto.ResponseMessage;
import fun.steven.bookstore.pojo.dto.cart.CartItemDto;
import fun.steven.bookstore.pojo.dto.cart.GetCartDto;
import fun.steven.bookstore.service.ICartService;
import fun.steven.bookstore.utils.annotation.CurrentUserId;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping("/add")
    public ResponseMessage<String> addToCart(@CurrentUserId Long userId, @RequestBody CartItemDto cartItemDto) {
        cartService.addToCart(userId, cartItemDto);
        return ResponseMessage.success("Item added to cart successfully", null);
    }

    @PostMapping("/update")
    public ResponseMessage<String> updateCartItem(@CurrentUserId Long userId, @RequestBody CartItemDto cartItemDto) {
        cartService.updateCartItem(userId, cartItemDto);
        return ResponseMessage.success("Cart item updated successfully", null);
    }

    @GetMapping
    public ResponseMessage<GetCartDto> getCart(@CurrentUserId Long userId) {
        GetCartDto cart = cartService.getCart(userId);
        return ResponseMessage.success("Cart retrieved successfully", cart);
    }
}
