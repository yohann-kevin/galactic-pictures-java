package com.kirua.galactic.exception;

public class InvalidUuidException extends Exception {

    public InvalidUuidException() {
        super();
    }

    public InvalidUuidException(String err) {
        super(err);
    }
}
