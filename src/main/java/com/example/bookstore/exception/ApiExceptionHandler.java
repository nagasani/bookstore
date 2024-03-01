package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        // Generic exception handling
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "An error occurred");
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Define other exception handlers here, as needed, for specific exceptions

    // Inner class for API error details
    public static class ApiError {

        private HttpStatus status;
        private String message;
        private String details;

        public ApiError(HttpStatus status, String message, String details) {
            super();
            this.status = status;
            this.message = message;
            this.details = details;
        }

        // Getters and Setters
    }
}
