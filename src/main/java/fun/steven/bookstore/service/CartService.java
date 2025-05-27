package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.dto.cart.AddCartItemDto;
import fun.steven.bookstore.dto.cart.GetCartDto;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public boolean addToCart(Long userId, AddCartItemDto cartItemDto) {
        Long cartId = userDao.getCartId(userId);
        return cartDao.addToCart(cartId, cartItemDto);
    }

    @Override
    public GetCartDto getCart(Long userId) {
        Long cartId = userDao.getCartId(userId);
        return cartDao.getCart(cartId);
    }
}
