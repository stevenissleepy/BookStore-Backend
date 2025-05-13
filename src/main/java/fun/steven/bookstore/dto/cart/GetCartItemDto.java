package fun.steven.bookstore.dto.cart;

import fun.steven.bookstore.dto.book.BookDto;
import lombok.Data;

@Data
public class GetCartItemDto {
    private BookDto book;
    private Integer quantity;
}
