package com.hammertime.hammertime2.exceptions;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(Long id) {
        super("Could not find client " + id);
    }
    public ClientNotFoundException(String email, String password) {
        super("Could not find client with email " + email + " and password " + password);
    }
}
