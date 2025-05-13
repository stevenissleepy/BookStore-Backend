package fun.steven.bookstore.dto.address;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    private String receiver;
    private String phone;
    private String address;

    public AddressDto(Address address) {
        BeanUtils.copyProperties(address, this);
    }
}
