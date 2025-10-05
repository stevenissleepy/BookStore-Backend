package fun.steven.bookstore.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dao.IAddressDao;
import fun.steven.bookstore.pojo.entity.Address;
import fun.steven.bookstore.repository.AddressRepository;

@Repository
public class AddressDao implements IAddressDao {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public boolean save(Address address) {
        return addressRepository.save(address) != null;
    }

    @Override
    public List<Address> findByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public boolean deleteByIdAndUserId(Long addressId, Long userId) {
        addressRepository.deleteByIdAndUserId(addressId, userId);
        return true;
    }
}
