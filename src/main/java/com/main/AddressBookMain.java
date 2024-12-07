package com.main;

import com.interfaces.ContactInterface;
import com.impl.ContactImpl;
import com.model.java.Contact;
import java.util.List;

public class AddressBookMain {
    public static void main(String[] args) {
        ContactInterface contactService = new ContactImpl();

        // Get all contacts
        List<Contact> contacts = contactService.fetchAll();
        System.out.println("\nAll Contacts:");
        for (Contact contact : contacts) {
            System.out.println(contact.getFirst_name() + " " + contact.getLast_name() + 
                             " - " + contact.getEmail()+" - " +contact.getPhone() +" - "+contact.getAddress()+" - "+contact.getNotes());
        }

        // Example of getting a contact by ID
        if (!contacts.isEmpty()) {
            int firstContactId = contacts.get(0).getId();
            Contact foundContact = contactService.fetchOne(firstContactId);
            if (foundContact != null) {
                System.out.println("\nFound contact by ID " + firstContactId + ": " + 
                    foundContact.getFirst_name() + " " + foundContact.getLast_name());
            }
        }
    }
}
