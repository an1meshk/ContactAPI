package com.animesh.contactapi.repo;

import com.animesh.contactapi.exception.ContactAPIException;
import com.animesh.contactapi.util.ContactRowMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static com.animesh.contactapi.util.ContactStub.CONTACT_NAME;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Animesh Kumar on 23-07-2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactRepoTest implements ContactRepoHelper {

    protected JdbcTemplate mockJdbcTemplate;
    protected ContactRepo contactRepo;

    @Before
    public void setup() {
        mockJdbcTemplate = Mockito.mock(JdbcTemplate.class);
        contactRepo = new ContactRepoImpl(mockJdbcTemplate);
    }

    @Test(expected = ContactAPIException.class)
    public void retrieveContactSuccess() throws ContactAPIException {
        RowMapper mapper = mock(RowMapper.class);
        when(mockJdbcTemplate.query(Mockito.anyString(), Mockito.any(ContactRowMapper.class), Mockito.anyString()))
                .thenThrow(DataAccessException.class);

        contactRepo.retrieveContact(CONTACT_NAME);
    }

}
