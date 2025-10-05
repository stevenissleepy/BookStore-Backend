package fun.steven.bookstore.dao.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fun.steven.bookstore.dao.IAddressDao;
import fun.steven.bookstore.pojo.dto.address.AddressDto;
import fun.steven.bookstore.pojo.dto.address.AddressesDto;
import fun.steven.bookstore.pojo.entity.Address;
import fun.steven.bookstore.pojo.entity.User;
import fun.steven.bookstore.repository.AddressRepository;
import fun.steven.bookstore.repository.UserRepository;

@Repository
public class AddressDao implements IAddressDao {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addAddress(Long userId, AddressDto addressDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        address.setUser(user);
        user.getAddresses().add(address);
        userRepository.save(user);

        return true;
    }

    @Override
    public AddressesDto getUserAddresses(Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        AddressesDto addressesDto = new AddressesDto(addresses);
        return addressesDto;
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
