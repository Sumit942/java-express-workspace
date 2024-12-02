package com.javaexpress.carts.exception;

public class CardAlreadyExistException extends RuntimeException{

    public CardAlreadyExistException(String msg) {
        super(msg);
    }
}
