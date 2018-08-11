package com.animesh.contactapi.repo;

/**
 * Created by Animesh Kumar on 14-04-2018.
 * <p>
 * Interface containing all SQL queries to be executed by repository
 * class.
 */
public enum ContactRepoHelper {
    /*CONTACT_INFO(ID, CONTACT_NAME, CONTACT_COMPANY, PROFILE_IMAGE, EMAIL,
    BIRTH_DATE, WORK_NUMBER, PERSONAL_NUMBER, STREET, STATE, CITY, PINCODE) */

    //create SQL query
    CREATE("insert into CONTACT_INFO values (?,?,?,?,?,?,?,?,?,?,?,?)"),
    //retrieve SQL query
    SELECT("select * from CONTACT_INFO where CONTACT_NAME =?"),
    //update query for passed contact name
    UPDATE("update CONTACT_INFO set CONTACT_NAME=?, CONTACT_COMPANY=?, PROFILE_IMAGE=?, EMAIL=?,BIRTH_DATE=?, WORK_PHONE=?, PERSONAL_PHONE=?, STREET=?, CITY=?, STATE=?, PINCODE=? where CONTACT_NAME =?"),
    //delete query for passed contact name
    DELETE("delete from CONTACT_INFO where CONTACT_NAME=?"),
    //search all contacts based upon passed email address
    SELECT_EML("select * from CONTACT_INFO where EMAIL =?"),
    //search all contacts based upon passed personal phone
    SELECT_P_NBR("select * from CONTACT_INFO where PERSONAL_PHONE =?"),
    //search all contacts based upon passed work phone
    SELECT_W_NBR("select * from CONTACT_INFO where WORK_PHONE =?"),
    //retrieve all contacts based upon passed state
    SELECT_ALL_STATE("select * from CONTACT_INFO where STATE =?"),
    //retrieve all contacts based upon passed city
    SELECT_ALL_CITY("select * from CONTACT_INFO where CITY =?");

    private String query;

    ContactRepoHelper(String query) {
        this.query = query;
    }

    public String query() {
        return query;
    }
}
