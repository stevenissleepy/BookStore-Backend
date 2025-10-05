package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.dto.cart.AddToCartRequestDto;
import fun.steven.bookstore.pojo.dto.cart.CartResponseDto;
import fun.steven.bookstore.pojo.dto.cart.UpdateCartRequestDto;
import fun.steven.bookstore.service.ICartService;
import fun.steven.bookstore.utils.annotation.CurrentUserId;
import fun.steven.bookstore.utils.annotation.UserOnly;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @UserOnly
    @PostMapping("/add")
    public ResponseMessage<String> addToCart(@CurrentUserId Long userId, @RequestBody AddToCartRequestDto cartItemDto) {
        cartService.addToCart(userId, cartItemDto);
        return ResponseMessage.success("成功添加到购物车", null);
    }

    @UserOnly
    @PostMapping("/update")
    public ResponseMessage<String> updateCartItem(@CurrentUserId Long userId, @RequestBody UpdateCartRequestDto cartItemDto) {
        cartService.updateCartItem(userId, cartItemDto);
        return ResponseMessage.success("成功更新购物车商品", null);
    }

    @UserOnly
    @GetMapping
    public ResponseMessage<CartResponseDto> getCart(@CurrentUserId Long userId) {
        CartResponseDto cart = cartService.getCart(userId);
        return ResponseMessage.success("成功获取购物车", cart);
    }
}
