package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.dto.ResponseMessage;
import fun.steven.bookstore.pojo.dto.cart.AddToCartRequestDto;
import fun.steven.bookstore.pojo.dto.cart.CartResponseDto;
import fun.steven.bookstore.pojo.dto.cart.UpdateCartRequestDto;
import fun.steven.bookstore.service.ICartService;
import fun.steven.bookstore.utils.annotation.CurrentUserId;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping("/add")
    public ResponseMessage<String> addToCart(@CurrentUserId Long userId, @RequestBody AddToCartRequestDto cartItemDto) {
        cartService.addToCart(userId, cartItemDto);
        return ResponseMessage.success("Item added to cart successfully", null);
    }

    @PostMapping("/update")
    public ResponseMessage<String> updateCartItem(@CurrentUserId Long userId, @RequestBody UpdateCartRequestDto cartItemDto) {
        cartService.updateCartItem(userId, cartItemDto);
        return ResponseMessage.success("Cart item updated successfully", null);
    }

    @GetMapping
    public ResponseMessage<CartResponseDto> getCart(@CurrentUserId Long userId) {
        CartResponseDto cart = cartService.getCart(userId);
        return ResponseMessage.success("Cart retrieved successfully", cart);
    }
}
