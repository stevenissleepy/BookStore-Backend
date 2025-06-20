package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.cart.AddToCartRequestDto;
import fun.steven.bookstore.pojo.dto.cart.CartResponseDto;
import fun.steven.bookstore.pojo.dto.cart.UpdateCartRequestDto;

public interface ICartService {
    boolean addToCart(Long userId, AddToCartRequestDto cartItemDto);
    boolean updateCartItem(Long userId, UpdateCartRequestDto cartItemDto);
    CartResponseDto getCart(Long userId);
}
