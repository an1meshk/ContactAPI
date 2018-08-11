package com.animesh.contactapi.util;

/**
 * Created by Animesh Kumar on 10-08-2018.
 */
public enum ContactAPIConstant {
    ERROR_MSG_SEARCH_PARAM_NULL("search parameter cannot be null"),
    ERROR_MSG_PRINT("printing exception object: "),
    ERROR_MSG_NO_DATA_FOUND("No data found");

    private String message;

    ContactAPIConstant(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

}
