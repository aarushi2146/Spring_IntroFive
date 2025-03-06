
/// /UC1
//package com.example.AddressBookApp.controller;
//import com.example.AddressBookApp.model.Address;
//import com.example.AddressBookApp.service.AddressService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/addresses")
//public class AddressController {
//
//    private final AddressService addressService;
//
//    @Autowired
//    public AddressController(AddressService addressService) {
//        this.addressService = addressService;
//    }
//
//    @PostMapping
//    public Address createAddress(@RequestBody Address address) {
//        return addressService.createAddress(address);
//    }
//
//    @GetMapping
//    public List<Address> getAllAddresses() {
//        return addressService.getAllAddresses();
//    }
//
//    @GetMapping("/{id}")
//    public Address getAddressById(@PathVariable Long id) {
//        return addressService.getAddressById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
//
//    @PutMapping("/{id}")
//    public Address updateAddress(@PathVariable Long id, @RequestBody Address address) {
//        return addressService.updateAddress(id, address);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteAddress(@PathVariable Long id) {
//        addressService.deleteAddress(id);
//    }
//}


////UC2
package com.example.AddressBookApp.controller;
import com.example.AddressBookApp.model.Address;
import com.example.AddressBookApp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Get all addresses
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id)
                .map(address -> new ResponseEntity<>(address, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new address
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address createdAddress = addressService.createAddress(address);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    // Update address by ID
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        if (!addressService.getAddressById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Address updatedAddress = addressService.updateAddress(id, address);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    // Delete address by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        if (!addressService.getAddressById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
