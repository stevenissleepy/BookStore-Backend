package fun.steven.bookstore.pojo.dto.address;

import fun.steven.bookstore.pojo.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAddressResponse {
    private String receiver;
    private String tel;
    private String address;

    public FindAddressResponse(Address address) {
        this.receiver = address.getReceiver();
        this.tel = address.getTel();
        this.address = address.getAddress();
    }
}
