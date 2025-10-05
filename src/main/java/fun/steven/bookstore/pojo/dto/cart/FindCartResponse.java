package fun.steven.bookstore.pojo.dto.cart;

import java.util.List;

import fun.steven.bookstore.pojo.dto.book.FindBookResponse;
import fun.steven.bookstore.pojo.entity.Cart;
import fun.steven.bookstore.pojo.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCartResponse {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class CartItemResponse {
        private FindBookResponse book;
        private Integer quantity;

        public CartItemResponse(CartItem cartItem) {
            this.book = new FindBookResponse(cartItem.getBook());
            this.quantity = cartItem.getQuantity();
        }
    }

    List<CartItemResponse> cart;

    public FindCartResponse(Cart cart) {
        this.cart = cart.getCartItems().stream().map(CartItemResponse::new).toList();
    }
}
