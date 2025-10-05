package fun.steven.bookstore.dao;

import java.util.List;

import fun.steven.bookstore.pojo.entity.Address;

public interface IAddressDao {
    boolean save(Address address);

    List<Address> findByUserId(Long userId);

    boolean deleteByIdAndUserId(Long addressId, Long userId);
}
