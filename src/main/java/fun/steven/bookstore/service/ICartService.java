package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.CartItemDto;
import fun.steven.bookstore.entity.Order;

public interface ICartService {
    boolean addToCart(CartItemDto cartItemDto);
    Order cartToOrder(Long userId);
}
