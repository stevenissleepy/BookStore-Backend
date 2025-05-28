package fun.steven.bookstore.dao;

import fun.steven.bookstore.dto.cart.AddCartItemDto;
import fun.steven.bookstore.dto.cart.GetCartDto;

public interface ICartDao {
    boolean addToCart(Long cartId, AddCartItemDto cartItemDto);

    boolean deleteFromCart(Long cartId, Long bookId);

    GetCartDto getCart(Long cartId);
}