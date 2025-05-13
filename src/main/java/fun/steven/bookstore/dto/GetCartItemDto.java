package fun.steven.bookstore.dto;

import lombok.Data;

@Data
public class GetCartItemDto {
    private BookDto book;
    private Integer quantity;
}
