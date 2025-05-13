package fun.steven.bookstore.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.steven.bookstore.dto.ResponseMessage;
import fun.steven.bookstore.dto.address.AddressDto;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/address")
public class AddressController {
    @PostMapping
    public ResponseMessage<String> addAddress(@RequestBody AddressDto addressDto) {
        return ResponseMessage.success("add address success!", null);
    }
}
