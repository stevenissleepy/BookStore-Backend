package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.address.AddressDto;
import fun.steven.bookstore.dto.address.GetAddressesDto;

public interface IAddressService {
    public boolean addAddress(AddressDto addressDto);

    public GetAddressesDto getUserAddresses(Long userId);
}
