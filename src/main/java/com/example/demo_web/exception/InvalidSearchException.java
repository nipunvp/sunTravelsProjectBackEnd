package com.example.demo_web.exception;

/**
 * Custom exception thrown when an invalid search operation is encountered.
 *
 * <p>This exception is typically used to indicate that the search parameters provided
 * are invalid or cannot be processed correctly.</p>
 */
public class InvalidSearchException extends RuntimeException {

    /**
     * Constructs a new {@code InvalidSearchException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception.
     */
    public InvalidSearchException(String message) {
        super(message);
    }
}
