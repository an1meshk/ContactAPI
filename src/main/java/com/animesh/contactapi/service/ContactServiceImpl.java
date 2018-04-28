package com.animesh.contactapi.service;

import com.animesh.contactapi.exception.ContactAPIException;
import com.animesh.contactapi.repo.ContactRepo;
import com.animesh.contactapi.vo.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Animesh Kumar on 14-04-2018.
 *
 * Business layer class containing implementation of ContactService
 * Interface. It invokes different methods of the ContactRepo to
 * perform Contact API operations.
 */
@Service
public class ContactServiceImpl implements ContactService {

    private Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Autowired
    private ContactRepo contactRepo;

    /**
     * Creates contact record in the database, throws ContactAPIException
     * @param contact
     * @throws ContactAPIException
     */
    @Override
    public void createContact(Contact contact) throws ContactAPIException {
        int result = contactRepo.createContact(contact);
        logger.info("created record with: "+contact);
    }

    /**
     * Retrieve a contact when contact name of existing record is passed, it returns
     * multiple results in case multiple records are found with the same name.
     * @param contactName
     * @return Set<Contact>
     * @throws ContactAPIException
     */
    @Override
    public Set<Contact> retrieveContact(String contactName) throws ContactAPIException {
        Set<Contact> contacts = contactRepo.retrieveContact(contactName);
        if(contacts.size() ==0) throw new ContactAPIException("No data found");
        return contacts;
    }

    /**
     * Updates contact when contact name of existing record is passed.
     * @param contactName
     * @param contact
     * @return result
     * @throws ContactAPIException
     */
    @Override
    public int updateContact(String contactName, Contact contact) throws ContactAPIException {
        int result = contactRepo.updateContact(contactName,contact);
        logger.info("no. of record updated: "+ result+
                "\n" +
                        "updated record with: " +contact);
        if(result <1) throw new ContactAPIException("No data updated, as no data found for passed value");
        return result;
    }

    /**
     * Deletes contact when contact name of existing record is passed
     * @param contactName
     * @throws ContactAPIException
     */
    @Override
    public void deleteContact(String contactName)throws ContactAPIException {
        int result = contactRepo.deleteContact(contactName);
        logger.info("no. of record deleted: "+ result);
        if(result <1) throw new ContactAPIException("No data deleted, as no data found for passed value");
    }

    /**
     * Search for a record by email or phone number or work phone. Throws
     * no data found message for no record found in the database.
     * @param contactVal
     * @param identifier
     * @return Set<Contact>
     * @throws ContactAPIException
     */
    @Override
    public Set<Contact> searchById(String contactVal, String identifier) throws ContactAPIException{
        Set<Contact> contacts = contactRepo.searchById(contactVal, identifier);
        logger.info("no. of record found: "+ contacts.size());
        if(contacts.size() == 0) throw new ContactAPIException("No data found");
        return contacts;
    }

    /**
     * Retrieves all records with the same state or city. Throws
     * no data found message for no record found in the database.
     * @param contactVal
     * @param identifier
     * @return result
     * @throws ContactAPIException
     */
    @Override
    public Set<Contact> retrieveAllContact(String contactVal, String identifier) throws ContactAPIException {
        Set<Contact> contacts = contactRepo.retrieveAllContact(contactVal, identifier);
        logger.info("no. of record found: "+ contacts.size());
        if(contacts.size() ==0) throw new ContactAPIException("No data found");
        return contacts;
    }
}
