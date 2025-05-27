package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.dto.address.AddressDto;
import fun.steven.bookstore.entity.Address;

public interface IAddressDao {
    boolean addAddress(Long userId, AddressDto addressDto);

    Address getAddressById(Long addressId);
    List<Address> getUserAddresses(Long userId);

    boolean deleteAddress(Long userId, Long addressId);
}
