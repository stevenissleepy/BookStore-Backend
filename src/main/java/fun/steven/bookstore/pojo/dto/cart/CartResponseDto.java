package fun.steven.bookstore.pojo.dto.cart;

import java.util.List;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.entity.Cart;
import fun.steven.bookstore.pojo.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class CartItemResponseDto {
        private BookDto book;
        private Integer quantity;

        public CartItemResponseDto(CartItem cartItem) {
            this.book = new BookDto(cartItem.getBook());
            this.quantity = cartItem.getQuantity();
        }
    }

    List<CartItemResponseDto> cart;

    public CartResponseDto(Cart cart) {
        this.cart = cart.getCartItems().stream().map(CartItemResponseDto::new).toList();
    }
}
