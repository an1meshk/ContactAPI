package com.animesh.contactapi.repo;

import com.animesh.contactapi.exception.ContactAPIException;
import com.animesh.contactapi.vo.Contact;

import java.util.Set;

/**
 * Created by Animesh Kumar on 14-04-2018.
 *
 * Repository interface defining the contract for the implementing
 * classes. It declares the methods need to perform Contact API operations.
 */
public interface ContactRepo {
    /**
     * Creates contact record in the database, throws ContactAPIException
     * for any exception occurred.
     * @param contact
     * @return result
     * @throws ContactAPIException
     */
    int createContact(Contact contact) throws ContactAPIException;

    /**
     * Retrieve a contact when contact name of existing record is passed, it returns
     * multiple results in case multiple records are found with the same name.
     * @param contactName
     * @return Set<Contact>
     * @throws ContactAPIException
     */
    Set<Contact> retrieveContact(String contactName) throws ContactAPIException;

    /**
     * Updates contact when contact name of existing record is passed.
     * @param contactName
     * @param contact
     * @return result
     * @throws ContactAPIException
     */
    int updateContact(String contactName, Contact contact) throws ContactAPIException;

    /**
     * Deletes contact when contact name of existing record is passed
     * @param contactName
     * @return result
     * @throws ContactAPIException
     */
    int deleteContact(String contactName) throws ContactAPIException;

    /**
     * Search for a record by email or phone number or work phone.
     * @param contactVal
     * @param identifier
     * @return Set<Contact>
     * @throws ContactAPIException
     */
    Set<Contact> searchById(String contactVal, String identifier) throws ContactAPIException;

    /**
     * Retrieves all records with the same state or city.
     * @param contactVal
     * @param identifier
     * @return Set<Contact>
     * @throws ContactAPIException
     */
    Set<Contact> retrieveAllContact(String contactVal, String identifier) throws ContactAPIException;
}
