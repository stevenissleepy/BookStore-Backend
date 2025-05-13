package fun.steven.bookstore.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IBookDao;
import fun.steven.bookstore.dao.ICartDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.dto.AddCartItemDto;
import fun.steven.bookstore.dto.BookDto;
import fun.steven.bookstore.dto.GetCartDto;
import fun.steven.bookstore.dto.GetCartItemDto;
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
    public boolean addToCart(AddCartItemDto cartItemDto) {
        Book book = bookDao.getBookById(cartItemDto.getBookId());
        Cart cart = userDao.getCart(cartItemDto.getUserId());
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
    // 获取用户的购物车及其商品
    Cart cart = userDao.getCart(userId);
    List<CartItem> cartItems = cartDao.getCartItems(cart);

    // 转换购物车商品为 DTO 对象
    List<GetCartItemDto> items = cartItems.stream().map(cartItem -> {
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(cartItem.getBook(), bookDto);

        GetCartItemDto itemDto = new GetCartItemDto();
        itemDto.setBook(bookDto);
        itemDto.setQuantity(cartItem.getQuantity());
        return itemDto;
    }).toList();

    // 构建返回的购物车 DTO
    return new GetCartDto(items);
}
}
