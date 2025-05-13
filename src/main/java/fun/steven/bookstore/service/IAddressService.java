package fun.steven.bookstore.service;

import fun.steven.bookstore.dto.address.AddressDto;

public interface IAddressService {
    public boolean addAddress(AddressDto addressDto);
}
