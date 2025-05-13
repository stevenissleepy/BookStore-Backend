package fun.steven.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.dto.cart.AddCartItemDto;
import fun.steven.bookstore.dto.cart.GetCartDto;
import fun.steven.bookstore.entity.Book;
import fun.steven.bookstore.entity.Cart;
import fun.steven.bookstore.entity.CartItem;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IBookDao bookDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public boolean addToCart(Long userId, AddCartItemDto cartItemDto) {
        Book book = bookDao.getBookById(cartItemDto.getBookId());
        Cart cart = userDao.getCart(userId);
        CartItem cartItem = cartDao.getCartItem(book, cart);
        
        /* 如果购物车中没有这本书 */
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setCart(cart);
            cartItem.setQuantity(cartItemDto.getQuantity());
            return cartDao.addToCart(cartItem) != null;
        
        /* 如果购物车中已经有这本书 */
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
            return cartDao.updateCartItem(cartItem);
        }
    }

@Override
public GetCartDto getCart(Long userId) {
    Cart cart = userDao.getCart(userId);
    return new GetCartDto(cart);
}
}
