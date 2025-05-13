package fun.steven.bookstore.dao;

import fun.steven.bookstore.entity.Address;

public interface IAddressDao {
    boolean addAddress(Address address);
    Address getAddressById(Long addressId);
}
