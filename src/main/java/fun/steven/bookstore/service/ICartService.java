package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.cart.CartItemDto;
import fun.steven.bookstore.dto.cart.GetCartDto;

public interface ICartService {
    boolean addToCart(Long userId, CartItemDto cartItemDto);
    GetCartDto getCart(Long userId);
}
