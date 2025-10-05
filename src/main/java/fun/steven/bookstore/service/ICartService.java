package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.cart.AddToCartRequest;
import fun.steven.bookstore.pojo.dto.cart.FindCartResponse;
import fun.steven.bookstore.pojo.dto.cart.UpdateCartRequest;

public interface ICartService {
    boolean addToCart(AddToCartRequest request);

    boolean updateCartItem(UpdateCartRequest request);

    FindCartResponse findUserCart(Long userId);
}
