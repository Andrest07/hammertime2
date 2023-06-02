package com.hammertime.hammertime2.exceptions;

public class JobNotFoundException extends Exception {
    public JobNotFoundException(Long id) {
        super("Could not find job " + id);
    }
}
