package com.animesh.contactapi.repo;

import com.animesh.contactapi.exception.ContactAPIException;
import com.animesh.contactapi.util.ContactRowMapper;
import com.animesh.contactapi.vo.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.animesh.contactapi.util.ContactStub.CONTACT_NAME;
import static org.mockito.Mockito.when;

/**
 * Created by Animesh Kumar on 23-07-2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactRepoTest {

    protected JdbcTemplate mockJdbcTemplate;
    protected ContactRepo contactRepo;

    @Before
    public void setup() {
        mockJdbcTemplate = Mockito.mock(JdbcTemplate.class);
        contactRepo = new ContactRepoImpl(mockJdbcTemplate);
    }

    @Test
    public void retrieveContactSuccess() throws ContactAPIException {
        Contact contact = new Contact();

        List<Contact> contactSet = new ArrayList<>();

        when(mockJdbcTemplate.query(Mockito.anyString(), Mockito.any(ContactRowMapper.class), Mockito.anyString()))
                .thenReturn(contactSet);

        Set result = contactRepo.retrieveContact(CONTACT_NAME);

        Assert.assertNotNull(result);
    }

}
