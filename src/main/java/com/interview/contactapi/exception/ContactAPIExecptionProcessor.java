package com.interview.contactapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Animesh Kumar on 14-04-2018.
 *
 * Contact API exception handler class, converts the
 * exception object into generic error message.
 */
@ControllerAdvice
public class ContactAPIExecptionProcessor {

    private Logger logger = LoggerFactory.getLogger(ContactAPIExecptionProcessor.class);

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    /**
     * Intercepts the ContactAPIException class object and
     * returns generic error message to the client.
     * @param ContactAPIException
     * @return String
     */
    public String exception(ContactAPIException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    /**
     * Intercepts the Exception class object and
     * returns generic error message to the client.
     * @param Exception
     * @return String
     */
    public String exception(Exception ex) {
        logger.error("printing exception object: ",ex);
        return ex.getMessage();
    }

}
