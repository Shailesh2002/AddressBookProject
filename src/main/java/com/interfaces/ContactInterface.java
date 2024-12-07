package com.interfaces;

import java.util.List;
import com.model.java.Contact;

public interface ContactInterface {
    int insert(Contact contact);
    List<Contact> fetchAll();
    Contact fetchOne(int contactId);
    int deleteContact(int contactId);
    int updateContact(Contact contact);
}
