package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.entity.Address;

public interface IAddressDao {
    boolean addAddress(Address address);

    Address getAddressById(Long addressId);
    List<Address> getUserAddresses(Long userId);
}
