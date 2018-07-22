package com.animesh.contactapi.controller;

import com.animesh.contactapi.exception.ContactAPIException;
import com.animesh.contactapi.service.ContactService;
import com.animesh.contactapi.vo.Contact;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * Created by Animesh Kumar on 13-04-2018.
 *
 * Contact API controller class containing definition of different endpoints
 * to perform several operations. Context root is "/contact".
 */
@RestController
@RequestMapping(value = "/contact")
@Api(value="contactapi", description="Contact API performs following operations:\n" +
        "1. Create a contact record\n" +
        "2. Retrieve a contact record\n" +
        "3. Update a contact record\n" +
        "4. Delete a contact record\n" +
        "5. Search for a record by email or phone number\n" +
        "6. Retrieve all records from the same state or city")
public class ContactController {

    @Autowired
    private ContactService contactService;


    @ApiOperation(value = "Create a contact by passing all the required values")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation was successful"),
            @ApiResponse(code = 201, message = "Created contact successfully"),
            @ApiResponse(code = 401, message = "You are not authorized to perform operation"),
            @ApiResponse(code = 403, message = "The resource you were trying access is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
        }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json")
    /**
     * Create a record in the database with passed value after input validation
     * is successful. Returns HTTP 201 for successful operation status.
     * @param Contact
     */
    public void createContact(@Valid @RequestBody Contact contact, Errors errors) throws ContactAPIException {
        if (errors.hasErrors()) {
            String fields = "";
            for(FieldError fieldError: errors.getFieldErrors()){
                fields += " | "+fieldError.getField();
            }
            throw new ContactAPIException("Input validation error for: "+fields);
        }
       contactService.createContact(contact);
    }

    @ApiOperation(value = "Retrieves contact when contact name of existing record is passed")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "$contactName", value = "Contact Name", required = true, dataType = "string", defaultValue = "John"),
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/{$contactName}",produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Retrieve a contact when contact name of existing record is passed, it returns
     * multiple results in case multiple records are found with the same name.
     * @return Contact
     * @param contactName
     */
    public Set<Contact> retrieveContact(@PathVariable("$contactName") String contactName) throws ContactAPIException{
        if(contactName==null) throw new ContactAPIException("search parameter cannot be null");
        return contactService.retrieveContact(contactName);
    }

    @ApiOperation(value = "Updates contact when contact name of existing record is passed")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "$contactName", value = "Contact Name", required = true, dataType = "string", defaultValue = "John"),
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value="/{$contactName}",produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Updates contact when contact name of existing record is passed.
     * @param contactName
     * @param contact
     */
    public void updateContact(@PathVariable("$contactName") String contactName,
                              @Valid @RequestBody Contact contact,Errors errors) throws ContactAPIException{
        if(contactName==null) throw new ContactAPIException("search parameter cannot be null");
        if (errors.hasErrors()) {
            String fields = "";
            for(FieldError fieldError: errors.getFieldErrors()){
                fields += " | "+fieldError.getField();
            }
            throw new ContactAPIException("Input validation error for: "+fields);
        }
        contactService.updateContact(contactName,contact);
    }

    @ApiOperation(value = "Deletes contact when contact name of existing record is passed")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "$contactName", value = "Contact Name", required = true, dataType = "string", defaultValue = "John"),
            }
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value="/{$contactName}")
    /**
     * Deletes contact when contact name of existing record is passed
     * @param contactName
     */
    public void deleteContact(@PathVariable("$contactName") String contactName) throws ContactAPIException{
        if(contactName==null) throw new ContactAPIException("search parameter cannot be null");
        contactService.deleteContact(contactName);
    }

    @ApiOperation(value = "Search for a record by email or phone number or work phone")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "$contactVal", value = "Search String", required = true, dataType = "string", defaultValue = "john@gmail.com"),
                    @ApiImplicitParam(name = "$id", value = "identifier", required = true, dataType = "string", defaultValue = "email", allowableValues = "email, workphone, personalphone"),
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/search/{$contactVal}/{$id}",produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Search for a record by email or phone number or work phone.
     * Email, work phone and personal phone are the allowed values of the identifier.
     * @return Set<Contact>
     * @param contactVal
     * @param identifier
     */
    public Set<Contact> searchByField(@PathVariable("$contactVal") String contactVal,
                                 @PathVariable("$id") String identifier) throws ContactAPIException{
        if(contactVal==null || identifier==null) throw new ContactAPIException("search parameter/identifier cannot be null");
        return contactService.searchById(contactVal, identifier);
    }

    @ApiOperation(value = "Retrieves all records with the same state or city")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "$contactVal", value = "Search String", required = true, dataType = "string", defaultValue = "Chicago"),
                    @ApiImplicitParam(name = "$id", value = "identifier", required = true, dataType = "string", defaultValue = "city", allowableValues = "state, city"),
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/searchall/{$contactVal}/{$id}",produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Retrieves all records with the same state or city. City and State
     * are the allowed values of the identifier.
     * @return Set<Contact>
     * @param contactVal
     * @param identifier
     */
    public Set<Contact> retrieveAllContact(@PathVariable("$contactVal") String contactVal,
                                           @PathVariable("$id") String identifier) throws ContactAPIException{
        if(contactVal==null || identifier==null) throw new ContactAPIException("search parameter/identifier cannot be null");
        return contactService.retrieveAllContacts(contactVal, identifier);
    }


}
