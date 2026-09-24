package com.personal.tasklist.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Resource with id " + id + " not found.");
    }
}
