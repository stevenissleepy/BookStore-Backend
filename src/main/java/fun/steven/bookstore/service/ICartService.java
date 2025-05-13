package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.AddCartItemDto;
import fun.steven.bookstore.dto.GetCartDto;

public interface ICartService {
    boolean addToCart(AddCartItemDto cartItemDto);
    GetCartDto getCart(Long userId);
}
