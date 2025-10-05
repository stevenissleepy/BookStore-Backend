package fun.steven.bookstore.service;

import fun.steven.bookstore.pojo.dto.address.AddAddressRequest;
import fun.steven.bookstore.pojo.dto.address.FindAddressesResponse;

public interface IAddressService {
    public boolean add(AddAddressRequest request);

    public boolean deleteAddress(Long addressId, Long userId);

    public FindAddressesResponse findUserAddresses(Long userId);
}
