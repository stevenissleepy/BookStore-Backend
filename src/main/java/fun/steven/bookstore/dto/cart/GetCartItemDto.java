package fun.steven.bookstore.dto.cart;

import fun.steven.bookstore.dto.book.BookDto;
import fun.steven.bookstore.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCartItemDto {
    private BookDto book;
    private Integer quantity;

    public GetCartItemDto(CartItem cartItem) {
        this.book = new BookDto(cartItem.getBook());
        this.quantity = cartItem.getQuantity();
    }
}
