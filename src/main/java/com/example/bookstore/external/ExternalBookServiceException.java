package com.example.bookstore.external;

public class ExternalBookServiceException extends RuntimeException 
{
    public ExternalBookServiceException(String message) {
        super(message);
    }

    public ExternalBookServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
