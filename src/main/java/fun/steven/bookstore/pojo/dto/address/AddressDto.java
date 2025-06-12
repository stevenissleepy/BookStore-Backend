package fun.steven.bookstore.pojo.dto.address;

import org.springframework.beans.BeanUtils;

import fun.steven.bookstore.pojo.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String receiver;
    private String tel;
    private String address;

    public AddressDto(Address address) {
        BeanUtils.copyProperties(address, this);
    }
}
