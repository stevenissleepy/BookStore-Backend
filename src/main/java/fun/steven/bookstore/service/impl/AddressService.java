package fun.steven.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IAddressDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.pojo.dto.address.AddAddressRequest;
import fun.steven.bookstore.pojo.dto.address.FindAddressesResponse;
import fun.steven.bookstore.pojo.entity.Address;
import fun.steven.bookstore.pojo.entity.User;
import fun.steven.bookstore.service.IAddressService;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private IAddressDao addressDao;

    @Autowired
    private IUserDao userDao;

    @Override
    public boolean add(AddAddressRequest request) {
        Long userId = request.getUserId();
        User user = userDao.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        Address address = new Address();
        BeanUtils.copyProperties(request, address);
        address.setUser(user);
        user.getAddresses().add(address);
        return userDao.save(user);
    }

    @Override
    public boolean deleteAddress(Long userId, Long addressId) {
        return addressDao.deleteByIdAndUserId(addressId, userId);
    }

    @Override
    public FindAddressesResponse findUserAddresses(Long userId) {
        List<Address> addresses = addressDao.findByUserId(userId);
        return new FindAddressesResponse(addresses);
    }
}
