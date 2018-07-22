package com.animesh.contactapi.service;

import com.animesh.contactapi.exception.ContactAPIException;
import com.animesh.contactapi.repo.ContactRepo;
import com.animesh.contactapi.vo.Address;
import com.animesh.contactapi.vo.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Stubber;
import org.mockito.verification.VerificationMode;
import org.mockito.verification.VerificationStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Animesh Kumar on 21-07-2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    private final static int ONE = 1;
    private final static String CONTACT_NAME = "john";
    private final static Contact MOCK_CONTACT = createContactStub();
    private final static Set<Contact> MOCK_CONTACT_SET = new HashSet<>();
    private static final int ZERO = 0;

    static {
        MOCK_CONTACT_SET.add(createContactStub());
    }

    //@Mock
    private static ContactRepo mockRepo;
    private static ContactService contactService;

    @Before
    public void setup() {
        mockRepo = Mockito.mock(ContactRepo.class);
        contactService = new ContactServiceImpl(mockRepo);

        /*
         another way of creating mock object when @Mock is used
         MockitoAnnotations.initMocks(this);
       */
    }

    @Test(expected = ContactAPIException.class)
    public void retrieveAllContactsFailNomatchingDataFound() throws ContactAPIException {
        String contactVal = "john@google.com";
        String identifier = "email";
        when(mockRepo.retrieveAllContacts(contactVal, identifier)).thenReturn(new HashSet<>());

        Set<Contact> contacts = contactService.retrieveAllContacts(contactVal, identifier);
    }

    @Test
    public void retrieveAllContactsSuccess() throws ContactAPIException {
        String contactVal = "john@google.com";
        String identifier = "email";
        when(mockRepo.retrieveAllContacts(contactVal, identifier)).thenReturn(MOCK_CONTACT_SET);

        Set<Contact> contacts = contactService.retrieveAllContacts(contactVal, identifier);

        assertEquals(MOCK_CONTACT_SET.size(), contacts.size());

        verify(mockRepo).retrieveAllContacts(contactVal, identifier);
    }

    @Test(expected = ContactAPIException.class)
    public void searchByIdFailNomatchingDataFound() throws ContactAPIException {
        String contactVal = "john@google.com";
        String identifier = "email";
        when(mockRepo.searchById(contactVal, identifier)).thenReturn(new HashSet<>());

        Set<Contact> contacts = contactService.searchById(contactVal, identifier);
    }

    @Test
    public void searchByIdSuccess() throws ContactAPIException {
        String contactVal = "john@google.com";
        String identifier = "email";
        when(mockRepo.searchById(contactVal, identifier)).thenReturn(MOCK_CONTACT_SET);

        Set<Contact> contacts = contactService.searchById(contactVal, identifier);

        assertEquals(MOCK_CONTACT_SET.size(), contacts.size());

        verify(mockRepo).searchById(contactVal, identifier);
    }

    @Test(expected = ContactAPIException.class)
    public void deleteContactThrowExceptionForNullValue() throws ContactAPIException {
        when(mockRepo.createContact(MOCK_CONTACT)).thenThrow(ContactAPIException.class);

        contactService.createContact(MOCK_CONTACT);
    }

    @Test
    public void deleteContactSuccess() throws ContactAPIException {
        when(mockRepo.createContact(MOCK_CONTACT)).thenReturn(ONE);

        contactService.createContact(MOCK_CONTACT);

        verify(mockRepo).createContact(MOCK_CONTACT);
    }

    @Test(expected = ContactAPIException.class)
    public void updateContactWhenRepoThrowsException() throws ContactAPIException {
        when(mockRepo.updateContact(CONTACT_NAME, MOCK_CONTACT)).thenThrow(ContactAPIException.class);

        contactService.updateContact(CONTACT_NAME, MOCK_CONTACT);
    }

    @Test(expected = ContactAPIException.class)
    public void updateContactFailWhenReturnZeroValue() throws ContactAPIException {
        when(mockRepo.updateContact(CONTACT_NAME, MOCK_CONTACT)).thenReturn(ZERO);

        contactService.updateContact(CONTACT_NAME, MOCK_CONTACT);
    }

    @Test
    public void updateContactSuccess() throws ContactAPIException {
        when(mockRepo.updateContact(CONTACT_NAME, MOCK_CONTACT)).thenReturn(ONE);

        int result = contactService.updateContact(CONTACT_NAME, MOCK_CONTACT);

        assertEquals(ONE, result);

        verify(mockRepo).updateContact(CONTACT_NAME, MOCK_CONTACT);
    }

    @Test(expected = ContactAPIException.class)
    public void createContactThrowExceptionForNullValue() throws ContactAPIException {
        when(mockRepo.createContact(MOCK_CONTACT)).thenThrow(ContactAPIException.class);

        contactService.createContact(MOCK_CONTACT);
    }

    @Test
    public void createContactSuccess() throws ContactAPIException {
        when(mockRepo.createContact(MOCK_CONTACT)).thenReturn(ONE);

        contactService.createContact(MOCK_CONTACT);

        verify(mockRepo).createContact(MOCK_CONTACT);
    }

    @Test(expected = ContactAPIException.class)
    public void retrieveContactRepoThrowsException() throws ContactAPIException {
        //when
        when(mockRepo.retrieveContact(CONTACT_NAME)).thenThrow(ContactAPIException.class);

        Set<Contact> contacts = contactService.retrieveContact(CONTACT_NAME);
    }

    @Test(expected = ContactAPIException.class)
    public void retrieveNoContactNull() throws ContactAPIException {
        //when
        when(mockRepo.retrieveContact(CONTACT_NAME)).thenReturn(null);

        Set<Contact> contacts = contactService.retrieveContact(CONTACT_NAME);
    }

    @Test(expected = ContactAPIException.class)
    public void retrieveContactZeroSize() throws ContactAPIException {
        //when
        when(mockRepo.retrieveContact(CONTACT_NAME)).thenReturn(new HashSet<>());

        Set<Contact> contacts = contactService.retrieveContact(CONTACT_NAME);
    }

    @Test
    public void retrieveContactSuccess() throws ContactAPIException {
        //when
        when(mockRepo.retrieveContact(CONTACT_NAME)).thenReturn(MOCK_CONTACT_SET);

        Set<Contact> contacts = contactService.retrieveContact(CONTACT_NAME);

        //then
        assertEquals(ONE, contacts.size());
        //assertThat(contacts, Matchers.hasItem(MOCK_CONTACT));

        //verify
        verify(mockRepo).retrieveContact(CONTACT_NAME);
    }

    private static Contact createContactStub() {
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
