package com.example.AddressBookApp.repository;

import com.example.AddressBookApp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
