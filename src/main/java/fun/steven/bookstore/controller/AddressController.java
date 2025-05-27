package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.dto.address.AddressDto;
import fun.steven.bookstore.dto.address.AddressesDto;
import fun.steven.bookstore.service.IAddressService;
import fun.steven.bookstore.utils.annotation.CurrentUserId;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private IAddressService addressService;

    @PostMapping
    public ResponseMessage<String> addAddress(@RequestBody AddressDto addressDto, @CurrentUserId Long userId) {
        addressService.addAddress(userId, addressDto);
        return ResponseMessage.success("add address success!", null);
    }

    @GetMapping
    public ResponseMessage<AddressesDto> getUserAddresses(@CurrentUserId Long userId) {
        AddressesDto addresses = addressService.getUserAddresses(userId);
        return ResponseMessage.success("get address success!", addresses);
    }

    @DeleteMapping("/{addressId}")
    public ResponseMessage<String> deleteAddress(@PathVariable Long addressId, @CurrentUserId Long userId) {
        addressService.deleteAddress(userId, addressId);
        return ResponseMessage.success("delete address success!", null);
    }
}
