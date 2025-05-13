package fun.steven.bookstore.dto.address;

import java.util.List;

import lombok.Data;

@Data
public class GetAddressesDto {
    private List<GetAddressDto> addresses;
}
