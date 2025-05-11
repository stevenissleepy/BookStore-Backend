package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.CartItemDto;

public interface ICartService {
    boolean addToCart(CartItemDto cartItemDto);
}
