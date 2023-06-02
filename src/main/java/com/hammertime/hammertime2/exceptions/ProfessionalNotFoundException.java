package com.hammertime.hammertime2.exceptions;

public class ProfessionalNotFoundException extends Exception {
    public ProfessionalNotFoundException(Long id) {
        super("Could not find professional " + id);
    }
    public ProfessionalNotFoundException(String email, String password) {
        super("Could not find professional with email " + email + " and password " + password);
    }
}
