package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.cart.AddCartItemDto;
import fun.steven.bookstore.dto.cart.GetCartDto;

public interface ICartService {
    boolean addToCart(Long userId, AddCartItemDto cartItemDto);
    GetCartDto getCart(Long userId);
}
