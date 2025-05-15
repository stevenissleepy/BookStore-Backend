package fun.steven.bookstore.dao;

import java.util.List;

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

    @Override
    public List<Address> getUserAddresses(Long userId) {
        return addressRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException("No address found")
        );
    }

    @Override
    public boolean deleteAddress(Long userId, Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(
                () -> new RuntimeException("Address not found"));

        if (address.getUser().getId() != userId) {
            throw new RuntimeException("You don't have permission to delete this address");
        }
        addressRepository.delete(address);
        return true;
    }
}
