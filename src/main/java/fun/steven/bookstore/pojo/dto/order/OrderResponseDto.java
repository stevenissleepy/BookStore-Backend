package fun.steven.bookstore.pojo.dto.order;

import java.util.List;

import fun.steven.bookstore.pojo.dto.book.BookDto;
import fun.steven.bookstore.pojo.entity.Order;
import fun.steven.bookstore.pojo.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class OrderItemResponseDto {
        private BookDto book;
        private Integer quantity;

        public OrderItemResponseDto(OrderItem orderItem) {
            this.book = new BookDto(orderItem.getBook());
            this.quantity = orderItem.getQuantity();
        }
    }

    private String address;
    private String tel;
    private String receiver;
    private Integer totalPrice;
    private List<OrderItemResponseDto> orderItems;
    private String date;

    public OrderResponseDto(Order order) {
        this.address = order.getAddress();
        this.tel = order.getTel();
        this.receiver = order.getReceiver();
        this.totalPrice = order.getTotalPrice();
        this.orderItems = order.getOrderItems().stream().map(OrderItemResponseDto::new).toList();
        this.date = order.getDate().toString().split("T")[0];
    }
}
