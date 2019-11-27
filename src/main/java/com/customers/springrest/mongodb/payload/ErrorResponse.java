package com.customers.springrest.mongodb.payload;

/**
 * Created by Jose Corominas on 11/27/19.
 */
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
