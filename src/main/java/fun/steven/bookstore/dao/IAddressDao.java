package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.pojo.dto.address.AddressDto;
import fun.steven.bookstore.pojo.entity.Address;

public interface IAddressDao {
    boolean addAddress(Long userId, AddressDto addressDto);

    List<Address> getUserAddresses(Long userId);

    boolean deleteAddress(Long userId, Long addressId);
}
