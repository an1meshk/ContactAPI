package com.interview.contactapi.exception;

/**
 * Created by Animesh Kumar on 14-04-2018.
 *
 * Contact API exception class
 */
public class ContactAPIException extends Exception {
    public ContactAPIException() {
        super();
    }

    public ContactAPIException(String message) {
        super(message);
    }

    public ContactAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactAPIException(Throwable cause) {
        super(cause);
    }

    protected ContactAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
