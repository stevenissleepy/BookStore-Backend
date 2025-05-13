package fun.steven.bookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.entity.Address;
import fun.steven.bookstore.repository.AddressRepository;

@Repository
public class AddressDao implements IAddressDao {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public boolean addAddress(Address address) {
        return addressRepository.save(address) != null;
    }

    @Override
    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(
                () -> new RuntimeException("Address not found"));
    }
}
