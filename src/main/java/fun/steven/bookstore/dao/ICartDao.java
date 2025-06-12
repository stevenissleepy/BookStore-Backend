package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.dto.cart.CartItemDto;
import fun.steven.bookstore.pojo.dto.cart.GetCartDto;

public interface ICartDao {
    boolean addToCart(Long cartId, CartItemDto cartItemDto);

    boolean deleteFromCart(Long cartId, Long bookId);

    boolean updateCartItem(Long cartId, CartItemDto cartItemDto);

    GetCartDto getCart(Long cartId);
}