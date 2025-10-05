package fun.steven.bookstore.pojo.dto.address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddAddressRequest {
    private Long userId;
    private String receiver;
    private String tel;
    private String address;
}
