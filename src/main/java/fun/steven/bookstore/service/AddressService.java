package fun.steven.bookstore.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.steven.bookstore.dao.IAddressDao;
import fun.steven.bookstore.dao.IUserDao;
import fun.steven.bookstore.dto.address.AddressDto;
import fun.steven.bookstore.entity.Address;
import fun.steven.bookstore.entity.User;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private IAddressDao addressDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public boolean addAddress(AddressDto addressDto) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        User user = userDao.getUserById(addressDto.getUserId());
        address.setUser(user);
        return addressDao.addAddress(address);
    }

}
