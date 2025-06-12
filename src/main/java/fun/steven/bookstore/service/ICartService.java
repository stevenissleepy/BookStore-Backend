package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.cart.CartItemDto;
import fun.steven.bookstore.pojo.dto.cart.GetCartDto;

public interface ICartService {
    boolean addToCart(Long userId, CartItemDto cartItemDto);
    boolean updateCartItem(Long userId, CartItemDto cartItemDto);
    GetCartDto getCart(Long userId);
}
