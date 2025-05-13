package fun.steven.bookstore.dto.address;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAddressDto {
    private String receiver;
    private String phone;
    private String address;

    public GetAddressDto(Address address) {
        BeanUtils.copyProperties(address, this);
    }
}
