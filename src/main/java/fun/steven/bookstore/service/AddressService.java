package fun.steven.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IAddressDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.dto.address.AddressDto;
import fun.steven.bookstore.dto.address.AddressesDto;
import fun.steven.bookstore.entity.Address;
import fun.steven.bookstore.entity.User;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private IAddressDao addressDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public boolean addAddress(Long userId, AddressDto addressDto) {
        Address address = new Address(addressDto);
        User user = userDao.getUserById(userId);
        address.setUser(user);
        return addressDao.addAddress(address);
    }

    @Override
    public AddressesDto getUserAddresses(Long userId) {
        List<Address> addresses = addressDao.getUserAddresses(userId);
        return new AddressesDto(addresses);
    }
}
