package fun.steven.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IAddressDao;
import fun.steven.bookstore.pojo.dto.address.AddressDto;
import fun.steven.bookstore.pojo.dto.address.AddressesDto;
import fun.steven.bookstore.pojo.entity.Address;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private IAddressDao addressDao;

    @Override
    public boolean addAddress(Long userId, AddressDto addressDto) {
        return addressDao.addAddress(userId, addressDto);
    }

    @Override
    public AddressesDto getUserAddresses(Long userId) {
        List<Address> addresses = addressDao.getUserAddresses(userId);
        return new AddressesDto(addresses);
    }

    @Override
    public boolean deleteAddress(Long userId, Long addressId) {
        return addressDao.deleteAddress(userId, addressId);
    }
}
