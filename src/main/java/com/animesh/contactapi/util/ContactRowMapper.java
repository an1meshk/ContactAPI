package com.animesh.contactapi.util;

import com.animesh.contactapi.vo.Address;
import com.animesh.contactapi.vo.Contact;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Animesh Kumar on 14-04-2018.
 * <p>
 * Spring JDBC RowMapper class, for converting data fetched from
 * database into domain object.
 */
public class ContactRowMapper implements RowMapper<Contact> {

    private Logger logger = LoggerFactory.getLogger(ContactRowMapper.class);

    /**
     * Call back method, called by the spring framework to convert fetched data into
     * domain object.
     *
     * @param rs
     * @param rowNum
     * @return Contact
     * @throws SQLException
     */
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

        //populate the temp variable with fetched data
        Integer id = rs.getInt("ID");
        String name = rs.getString("CONTACT_NAME");
        String company = rs.getString("CONTACT_COMPANY");

        InputStream is = rs.getBinaryStream("PROFILE_IMAGE");
        byte[] profileImg = null;
        try {
            profileImg = IOUtils.toByteArray(is);
        } catch (IOException io) {
            logger.error("unable to populate profile image, something went wrong", io);
        }

        String email = rs.getString("EMAIL");
        Date bDate = rs.getDate("BIRTH_DATE");
        String workPhone = rs.getString("WORK_PHONE");
        String personsalPhone = rs.getString("PERSONAL_PHONE");
        String street = rs.getString("STREET");
        String state = rs.getString("STATE");
        String city = rs.getString("CITY");
        String pincode = rs.getString("PINCODE");

        //create Address object with already populated temp variable
        Address address = new Address();
        address.setStreet(street);
        address.setState(state);
        address.setCity(city);
        address.setPincode(pincode);

        //create contact object with already populated temp variable and Address object
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setCompany(company);
        contact.setProfileImg(profileImg);
        contact.setEmail(email);
        contact.setBirthDate(bDate);
        contact.setWorkNumber(workPhone);
        contact.setPersonalNumber(personsalPhone);
        contact.setAddress(address);

        return contact;
    }

}
