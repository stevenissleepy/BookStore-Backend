package fun.steven.bookstore.dao;

import fun.steven.bookstore.pojo.dto.address.AddressDto;
import fun.steven.bookstore.pojo.dto.address.AddressesDto;

public interface IAddressDao {
    boolean addAddress(Long userId, AddressDto addressDto);

    AddressesDto getUserAddresses(Long userId);

    boolean deleteAddress(Long userId, Long addressId);
}
