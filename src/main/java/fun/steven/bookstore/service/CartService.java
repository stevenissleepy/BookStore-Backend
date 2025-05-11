package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dto.CartItemDto;
import fun.steven.bookstore.entity.CartItem;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IBookDao bookDao;

    @Override
    public boolean addToCart(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setBook(bookDao.getBookById(cartItemDto.getBookId()));
        cartItem.setCart(cartDao.getCartById(cartItemDto.getCartId()));

        return cartDao.addToCart(cartItem);
    }
}
