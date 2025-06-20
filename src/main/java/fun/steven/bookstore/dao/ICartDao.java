package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.dto.cart.AddToCartRequestDto;
import fun.steven.bookstore.pojo.dto.cart.CartResponseDto;
import fun.steven.bookstore.pojo.dto.cart.UpdateCartRequestDto;

public interface ICartDao {
    boolean addToCart(Long cartId, AddToCartRequestDto cartItemDto);

    boolean deleteFromCart(Long cartId, Long bookId);

    boolean updateCartItem(Long cartId, UpdateCartRequestDto cartItemDto);

    CartResponseDto getCart(Long cartId);
}