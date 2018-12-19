package com.sg.webservices.exception;

public class RelationshipFoundException extends Exception {

    public RelationshipFoundException(String message) {
        super(message);
    }

    public RelationshipFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
