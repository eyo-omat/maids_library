package com.maids.maids_library.exceptions;

public class PatronNotFoundException extends RuntimeException{
    public PatronNotFoundException(String message) {
        super(message);
    }
}
