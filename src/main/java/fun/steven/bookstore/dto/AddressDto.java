package fun.steven.bookstore.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Long userId;
    private String receiver;
    private String phone;
    private String address;
}
