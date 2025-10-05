package fun.steven.bookstore.pojo.dto.address;

import java.util.List;

import fun.steven.bookstore.pojo.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindAddressesResponse {
    private List<FindAddressResponse> addresses;

    public FindAddressesResponse(List<Address> addresses) {
        this.addresses = addresses.stream().map(FindAddressResponse::new).toList();
    }
}
