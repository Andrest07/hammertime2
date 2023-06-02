package com.hammertime.hammertime2.exceptions;

public class RatingNotFoundException extends Exception {
    public RatingNotFoundException(Long id) {
        super("Could not find rating " + id);
    }
}
