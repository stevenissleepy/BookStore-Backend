package fun.steven.bookstore.pojo.dto.address;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import fun.steven.bookstore.pojo.entity.Address;

@Data
@NoArgsConstructor
public class FindAddressesResponse {
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

    private List<FindAddressResponse> addresses;

    public FindAddressesResponse(List<Address> addresses) {
        this.addresses = addresses.stream().map(FindAddressResponse::new).toList();
    }
}
