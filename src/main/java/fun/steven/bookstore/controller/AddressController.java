package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.dto.address.AddressDto;
import fun.steven.bookstore.dto.address.GetAddressesDto;
import fun.steven.bookstore.service.IAddressService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private IAddressService addressService;

    @PostMapping
    public ResponseMessage<String> addAddress(@RequestBody AddressDto addressDto) {
        addressService.addAddress(addressDto);
        return ResponseMessage.success("add address success!", null);
    }

    @GetMapping("/{userId}")
    public ResponseMessage<GetAddressesDto> getUserAddresses(@PathVariable Long userId) {
        GetAddressesDto addresses = addressService.getUserAddresses(userId);
        return ResponseMessage.success("get address success!", addresses);
    }
}
