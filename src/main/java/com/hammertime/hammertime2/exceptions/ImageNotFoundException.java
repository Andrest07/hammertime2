package com.hammertime.hammertime2.exceptions;

public class ImageNotFoundException extends Exception {
    public ImageNotFoundException(Long id) {
        super("Could not find image." + id);
    }
}
