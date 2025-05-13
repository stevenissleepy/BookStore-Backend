package fun.steven.bookstore.dto.address;

import java.util.List;

import fun.steven.bookstore.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAddressesDto {
    private List<GetAddressDto> addresses;

    public GetAddressesDto(List<Address> addresses) {
        this.addresses = addresses.stream().map(GetAddressDto::new).toList();
    }
}
