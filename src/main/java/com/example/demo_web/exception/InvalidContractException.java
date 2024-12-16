package com.example.demo_web.exception;

/**
 * Exception thrown when a contract is invalid.
 * This exception is a custom runtime exception used to signal invalid contract operations.
 */
public class InvalidContractException extends RuntimeException {

    /**
     * Constructs a new InvalidContractException with the specified detail message.
     *
     * @param message the detail message, providing more information about the exception
     */
    public InvalidContractException(String message) {
        super(message);
    }
}
