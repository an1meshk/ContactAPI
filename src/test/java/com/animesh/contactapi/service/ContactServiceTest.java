package com.animesh.contactapi.service;

import com.animesh.contactapi.exception.ContactAPIException;
import com.animesh.contactapi.repo.ContactRepo;
import com.animesh.contactapi.vo.Contact;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static com.animesh.contactapi.util.ContactStub.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Animesh Kumar on 21-07-2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    //@Mock
    private static ContactRepo mockRepo;
    private static ContactService contactService;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        mockRepo = Mockito.mock(ContactRepo.class);
        contactService = new ContactServiceImpl(mockRepo);

        /*
         another way of creating mock object when @Mock is used
         MockitoAnnotations.initMocks(this);
       */
    }

    @Test
    public void retrieveAllContactsFailNomatchingDataFound() throws ContactAPIException {
        String contactVal = "john@google.com";
        String identifier = "email";

        thrown.expect(ContactAPIException.class);
        thrown.expectMessage("No data found");

        when(mockRepo.retrieveAllContacts(Mockito.anyString(), Mockito.anyString())).thenReturn(new HashSet<>());

        try {
            Set<Contact> contacts = contactService.retrieveAllContacts(contactVal, identifier);
        } finally {
            Mockito.verify(mockRepo).retrieveAllContacts(Mockito.anyString(), Mockito.anyString());
        }

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

        try {
            Set<Contact> contacts = contactService.searchById(contactVal, identifier);
        } finally {
            Mockito.verify(mockRepo).searchById(contactVal, identifier);
        }
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
    public void deleteContactThrowExceptionForNoDeletedRecord() throws ContactAPIException {
        when(mockRepo.deleteContact(Mockito.anyString())).thenReturn(ZERO);

        try {
            contactService.deleteContact(CONTACT_NAME);
        } finally {
            Mockito.verify(mockRepo).deleteContact(Mockito.anyString());
        }
    }

    @Test
    public void deleteContactSuccess() throws ContactAPIException {
        when(mockRepo.deleteContact(CONTACT_NAME)).thenReturn(ONE);

        contactService.deleteContact(CONTACT_NAME);

        verify(mockRepo).deleteContact(CONTACT_NAME);
    }

    @Test(expected = ContactAPIException.class)
    public void updateContactWhenRepoThrowsException() throws ContactAPIException {
        when(mockRepo.updateContact(CONTACT_NAME, MOCK_CONTACT)).thenThrow(ContactAPIException.class);

        try {
            contactService.updateContact(CONTACT_NAME, MOCK_CONTACT);
        } finally {
            Mockito.verify(mockRepo).updateContact(CONTACT_NAME, MOCK_CONTACT);
        }
    }

    @Test(expected = ContactAPIException.class)
    public void updateContactFailWhenReturnZeroValue() throws ContactAPIException {
        when(mockRepo.updateContact(CONTACT_NAME, MOCK_CONTACT)).thenReturn(ZERO);

        try {
            contactService.updateContact(CONTACT_NAME, MOCK_CONTACT);
        } finally {
            Mockito.verify(mockRepo).updateContact(CONTACT_NAME, MOCK_CONTACT);
        }
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

        try {
            contactService.createContact(MOCK_CONTACT);
        } finally {
            Mockito.verify(mockRepo).createContact(MOCK_CONTACT);
        }
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
        try {
            Set<Contact> contacts = contactService.retrieveContact(CONTACT_NAME);
        } finally {
            Mockito.verify(mockRepo).retrieveContact(CONTACT_NAME);
        }
    }

    @Test(expected = ContactAPIException.class)
    public void retrieveNoContactNull() throws ContactAPIException {
        //when
        when(mockRepo.retrieveContact(CONTACT_NAME)).thenReturn(null);
        try {
            Set<Contact> contacts = contactService.retrieveContact(CONTACT_NAME);
        } finally {
            Mockito.verify(mockRepo).retrieveContact(CONTACT_NAME);
        }
    }

    @Test(expected = ContactAPIException.class)
    public void retrieveContactZeroSize() throws ContactAPIException {
        //when
        when(mockRepo.retrieveContact(CONTACT_NAME)).thenReturn(new HashSet<>());
        try {
            Set<Contact> contacts = contactService.retrieveContact(CONTACT_NAME);
        } finally {
            Mockito.verify(mockRepo).retrieveContact(CONTACT_NAME);
        }
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


}
