package com.example.bookstore.exception;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String message) {
        super(message);
    }

    // You can also add a constructor to pass in the cause of the exception
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
