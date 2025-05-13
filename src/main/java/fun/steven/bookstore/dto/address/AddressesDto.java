package fun.steven.bookstore.dto.address;

import java.util.List;

import fun.steven.bookstore.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressesDto {
    private List<AddressDto> addresses;

    public AddressesDto(List<Address> addresses) {
        this.addresses = addresses.stream().map(AddressDto::new).toList();
    }
}
