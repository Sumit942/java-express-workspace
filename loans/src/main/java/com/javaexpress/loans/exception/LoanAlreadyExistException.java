package com.javaexpress.loans.exception;

public class LoanAlreadyExistException extends RuntimeException {

    public LoanAlreadyExistException(String msg) {
        super(msg);
    }
}
