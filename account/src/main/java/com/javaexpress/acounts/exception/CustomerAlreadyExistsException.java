package com.javaexpress.acounts.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String msg) {
        super(msg);
    }
}
