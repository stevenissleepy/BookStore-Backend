package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.dto.cart.AddToCartRequest;
import fun.steven.bookstore.pojo.dto.cart.FindCartResponse;
import fun.steven.bookstore.pojo.dto.cart.UpdateCartRequest;
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
    public ResponseMessage<String> addToCart(@CurrentUserId Long userId, @RequestBody AddToCartRequest request) {
        request.setUserId(userId);
        cartService.addToCart(request);
        return ResponseMessage.success("成功添加到购物车", null);
    }

    @UserOnly
    @PostMapping("/update")
    public ResponseMessage<String> updateCartItem(@CurrentUserId Long userId, @RequestBody UpdateCartRequest request) {
        request.setUserId(userId);
        cartService.updateCartItem(request);
        return ResponseMessage.success("成功更新购物车商品", null);
    }

    @UserOnly
    @GetMapping
    public ResponseMessage<FindCartResponse> getCart(@CurrentUserId Long userId) {
        FindCartResponse response = cartService.findUserCart(userId);
        return ResponseMessage.success("成功获取购物车", response);
    }
}
