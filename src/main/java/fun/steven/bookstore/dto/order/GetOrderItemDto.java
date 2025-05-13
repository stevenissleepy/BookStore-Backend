package fun.steven.bookstore.dto.order;

import fun.steven.bookstore.dto.book.BookDto;
import fun.steven.bookstore.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderItemDto {
    private BookDto book;
    private Integer quantity;

    public GetOrderItemDto(OrderItem orderItem) {
        this.book = new BookDto(orderItem.getBook());
        this.quantity = orderItem.getQuantity();
    }
}
