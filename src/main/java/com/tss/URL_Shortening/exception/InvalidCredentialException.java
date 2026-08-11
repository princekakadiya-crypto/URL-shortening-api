package com.tss.URL_Shortening.exception;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message) {
        super(message);
    }
}
