package com.customers.springrest.mongodb.exception;

/**
 * Created by rajeevkumarsingh on 22/10/17.
 */
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String tweetId) {
        super("Tweet not found with id " + tweetId);
    }
}
