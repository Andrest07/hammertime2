package com.hammertime.hammertime2.exceptions;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException(Long id) {
        super("Could not find transaction " + id);
    }
}
