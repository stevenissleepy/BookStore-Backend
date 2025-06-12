package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.address.AddressDto;
import fun.steven.bookstore.pojo.dto.address.AddressesDto;

public interface IAddressService {
    public boolean addAddress(Long userId, AddressDto addressDto);

    public AddressesDto getUserAddresses(Long userId);

    public boolean deleteAddress(Long userId, Long addressId);
}
