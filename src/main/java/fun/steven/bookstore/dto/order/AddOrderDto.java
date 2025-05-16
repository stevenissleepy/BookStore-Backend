package fun.steven.bookstore.dto.order;

import lombok.Data;

@Data
public class AddOrderDto {
    private String receiver;
    private String phone;
    private String address;
}
