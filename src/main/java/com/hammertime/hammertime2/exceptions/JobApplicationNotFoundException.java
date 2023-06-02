package com.hammertime.hammertime2.exceptions;

public class JobApplicationNotFoundException extends Exception {
    public JobApplicationNotFoundException(Long id) {
        super("Could not find job application " + id);
    }
}