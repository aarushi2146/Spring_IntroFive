package com.example.address_book_apps.service;



import com.example.address_book_apps.dto.ContactDTO;
import com.example.address_book_apps.model.Contact;

import java.util.List;

public interface IContactService {
    List<ContactDTO> getAllContacts();
    ContactDTO getContactById(Long id);
    ContactDTO createContact(ContactDTO contactDTO);
    ContactDTO updateContact(Long id, ContactDTO contactDTO);
    void deleteContact(Long id);
}