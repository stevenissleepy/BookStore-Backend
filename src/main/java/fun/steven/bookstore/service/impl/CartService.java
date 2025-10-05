package fun.steven.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.pojo.dto.cart.AddToCartRequestDto;
import fun.steven.bookstore.pojo.dto.cart.CartResponseDto;
import fun.steven.bookstore.pojo.dto.cart.UpdateCartRequestDto;
import fun.steven.bookstore.service.ICartService;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public boolean addToCart(Long userId, AddToCartRequestDto cartItemDto) {
        Long cartId = userDao.getCartId(userId);
        return cartDao.addToCart(cartId, cartItemDto);
    }

    @Override
    public boolean updateCartItem(Long userId, UpdateCartRequestDto cartItemDto) {
        Long cartId = userDao.getCartId(userId);
        return cartDao.updateCartItem(cartId, cartItemDto);
    }

    @Override
    public CartResponseDto getCart(Long userId) {
        Long cartId = userDao.getCartId(userId);
        return cartDao.getCart(cartId);
    }
}
