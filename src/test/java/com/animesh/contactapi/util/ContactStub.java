package com.animesh.contactapi.util;

import com.animesh.contactapi.vo.Address;
import com.animesh.contactapi.vo.Contact;

import java.util.*;

/**
 * Created by Animesh Kumar on 23-07-2018.
 */
public class ContactStub {

    public final static int ONE = 1;
    public static final int ZERO = 0;

    public final static String CONTACT_NAME = "john";
    public final static Contact MOCK_CONTACT = createContactStub();
    public final static Set<Contact> MOCK_CONTACT_SET = new HashSet<>();
    public final static List<Contact> MOCK_CONTACT_LIST = new ArrayList<>();

    static {
        MOCK_CONTACT_SET.add(MOCK_CONTACT);
    }

    static {
        MOCK_CONTACT_LIST.add(MOCK_CONTACT);
    }

    public static Contact createContactStub() {
        Address address = new Address();
        address.setCity("Chicago");
        address.setPincode("60020");
        address.setState("IL");
        address.setStreet("Mark Ln");

        Contact contact = new Contact();

        contact.setName("John");
        contact.setAddress(address);
        contact.setId(1);
        contact.setWorkNumber("2242011234");
        contact.setPersonalNumber("2242011234");
        contact.setCompany("Google");
        contact.setEmail("john@gmail.com");
        contact.setBirthDate(new Date(2000, 12, 13));

        return contact;
    }
}
