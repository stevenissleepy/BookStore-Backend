package fun.steven.bookstore.dto.address;

import lombok.Data;

@Data
public class GetAddressDto {
    private String receiver;
    private String phone;
    private String address;
}
