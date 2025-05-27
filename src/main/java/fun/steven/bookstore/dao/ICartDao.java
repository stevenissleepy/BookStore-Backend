package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.dto.cart.AddCartItemDto;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.CartItem;

public interface ICartDao {
    boolean addToCart(Long cartId, AddCartItemDto cartItemDto);

    boolean clear(Long cartId);

    List<CartItem> getCartItems(Cart cart);
}