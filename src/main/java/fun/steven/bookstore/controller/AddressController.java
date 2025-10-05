package fun.steven.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.pojo.ResponseMessage;
import fun.steven.bookstore.pojo.dto.address.AddAddressRequest;
import fun.steven.bookstore.pojo.dto.address.FindAddressesResponse;
import fun.steven.bookstore.service.IAddressService;
import fun.steven.bookstore.utils.annotation.CurrentUserId;
import fun.steven.bookstore.utils.annotation.UserOnly;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private IAddressService addressService;

    @UserOnly
    @PostMapping
    public ResponseMessage<String> addAddress(@CurrentUserId Long userId, @RequestBody AddAddressRequest request) {
        request.setUserId(userId);
        addressService.add(request);
        return ResponseMessage.success("add address success!", null);
    }

    @UserOnly
    @GetMapping
    public ResponseMessage<FindAddressesResponse> findUserAddresses(@CurrentUserId Long userId) {
        FindAddressesResponse response = addressService.findUserAddresses(userId);
        return ResponseMessage.success("get address success!", response);
    }

    @UserOnly
    @DeleteMapping("/{addressId}")
    public ResponseMessage<String> deleteAddress(@CurrentUserId Long userId, @PathVariable Long addressId) {
        addressService.deleteAddress(userId, addressId);
        return ResponseMessage.success("delete address success!", null);
    }
}
