package fun.steven.bookstore.dto.order;

import fun.steven.bookstore.dto.book.BookDto;
import lombok.Data;

@Data
public class GetOrderItemDto {
    private BookDto book;
    private Integer quantity;
}
