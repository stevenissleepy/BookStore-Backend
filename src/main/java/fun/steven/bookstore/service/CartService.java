package fun.steven.bookstore.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dto.CartItemDto;
import fun.steven.bookstore.entity.CartItem;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartDao cartDao;

    @Override
    public boolean addToCart(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        BeanUtils.copyProperties(cartItemDto, cartItem);

        return cartDao.addToCart(cartItem);
    }
}
