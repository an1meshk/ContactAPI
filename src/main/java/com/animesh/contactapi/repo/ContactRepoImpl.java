package com.animesh.contactapi.repo;

import com.animesh.contactapi.exception.ContactAPIException;
import com.animesh.contactapi.util.ContactAPIConstant;
import com.animesh.contactapi.util.ContactRowMapper;
import com.animesh.contactapi.vo.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Animesh Kumar on 14-04-2018.
 * <p>
 * Repository class JDBC code for performing different
 * CURD operations.
 */
@Repository
public class ContactRepoImpl implements ContactRepo {

    protected JdbcTemplate contactJdbcTemplate;
    protected RowMapper<Contact> rowMapper = new ContactRowMapper();
    private Logger logger = LoggerFactory.getLogger(ContactRepoImpl.class);

    @Autowired
    public ContactRepoImpl(JdbcTemplate contactJdbcTemplate) {
        this.contactJdbcTemplate = contactJdbcTemplate;
    }

    /**
     * Creates contact record in the database, throws ContactAPIException
     * for any exception occurred.
     *
     * @param contact
     * @return result
     * @throws ContactAPIException
     */
    @Override
    public int createContact(Contact contact) throws ContactAPIException {
        try {
            return contactJdbcTemplate.update(ContactRepoHelper.CREATE.query(), contact.getId(), contact.getName(), contact.getCompany(),
                    contact.getProfileImg(), contact.getEmail(), contact.getBirthDate(), contact.getPersonalNumber(),
                    contact.getWorkNumber(), contact.getAddress().getStreet(), contact.getAddress().getCity(),
                    contact.getAddress().getState(), contact.getAddress().getPincode());

        } catch (DataAccessException dae) {
            logger.error(ContactAPIConstant.ERROR_MSG_PRINT.message(), dae);
            throw new ContactAPIException("Unable to create contact");
        }
    }

    /**
     * Retrieve a contact when contact name of existing record is passed, it returns
     * multiple results in case multiple records are found with the same name.
     *
     * @param contactName
     * @return Set<Contact>
     * @throws ContactAPIException
     */
    @Override
    public Set<Contact> retrieveContact(String contactName) throws ContactAPIException {
        try {
            return new HashSet<>(contactJdbcTemplate.query(ContactRepoHelper.SELECT.query(), rowMapper, contactName));
        } catch (DataAccessException dae) {
            logger.error(ContactAPIConstant.ERROR_MSG_PRINT.message(), dae);
            throw new ContactAPIException("Unable to retrieve contact");
        }

    }

    /**
     * Updates contact when contact name of existing record is passed.
     *
     * @param contactName
     * @param contact
     * @return result
     * @throws ContactAPIException
     */
    @Override
    public int updateContact(String contactName, Contact contact) throws ContactAPIException {
        try {
            return contactJdbcTemplate.update(ContactRepoHelper.UPDATE.query(), contact.getName(), contact.getCompany(),
                    contact.getProfileImg(), contact.getEmail(), contact.getBirthDate(), contact.getWorkNumber(),
                    contact.getPersonalNumber(), contact.getAddress().getStreet(), contact.getAddress().getCity(),
                    contact.getAddress().getState(), contact.getAddress().getPincode(), contactName);
        } catch (DataAccessException dae) {
            logger.error(ContactAPIConstant.ERROR_MSG_PRINT.message(), dae);
            throw new ContactAPIException("Unable to update contact");
        }

    }

    /**
     * Deletes contact when contact name of existing record is passed
     *
     * @param contactName
     * @return result
     * @throws ContactAPIException
     */
    @Override
    public int deleteContact(String contactName) throws ContactAPIException {
        try {
            return contactJdbcTemplate.update(ContactRepoHelper.DELETE.query(), contactName);
        } catch (DataAccessException dae) {
            logger.error(ContactAPIConstant.ERROR_MSG_PRINT.message(), dae);
            throw new ContactAPIException("Unable to delete contact");
        }

    }

    /**
     * Search for a record by email or phone number or work phone.
     *
     * @param contactVal
     * @param identifier
     * @return Set<Contact>
     * @throws ContactAPIException
     */
    @Override
    public Set<Contact> searchById(String contactVal, String identifier) throws ContactAPIException {
        String SEARCH_BY_ID = ContactRepoHelper.SELECT_EML.query();

        if (identifier.equalsIgnoreCase("workphone")) {
            SEARCH_BY_ID = ContactRepoHelper.SELECT_W_NBR.query();
        } else if (identifier.equalsIgnoreCase("personalphone")) {
            SEARCH_BY_ID = ContactRepoHelper.SELECT_P_NBR.query();
        }

        try {
            return new HashSet<>(contactJdbcTemplate.query(SEARCH_BY_ID, rowMapper, contactVal));
        } catch (DataAccessException dae) {
            logger.error(ContactAPIConstant.ERROR_MSG_PRINT.message(), dae);
            throw new ContactAPIException("Unable to retrieve contact by " + identifier);
        }

    }

    /**
     * Retrieves all records with the same state or city.
     *
     * @param contactVal
     * @param identifier
     * @return Set<Contact>
     * @throws ContactAPIException
     */
    @Override
    public Set<Contact> retrieveAllContacts(String contactVal, String identifier) throws ContactAPIException {
        String SELECT_ALL = ContactRepoHelper.SELECT_ALL_STATE.query();

        if (identifier.equalsIgnoreCase("city")) {
            SELECT_ALL = ContactRepoHelper.SELECT_ALL_CITY.query();
        }
        try {
            return new HashSet<>(contactJdbcTemplate.query(SELECT_ALL, rowMapper, contactVal));
        } catch (DataAccessException dae) {
            logger.error(ContactAPIConstant.ERROR_MSG_PRINT.message(), dae);
            throw new ContactAPIException("Unable to retrieve all contact by " + identifier);
        }
    }
}
