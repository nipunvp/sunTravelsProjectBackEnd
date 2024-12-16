package com.example.demo_web.exception;

/**
 * Exception thrown when a contract is not found.
 * <p>
 * This exception is typically used in scenarios where a requested contract does not exist in the database.
 * It extends {@link RuntimeException}, so it is an unchecked exception.
 * </p>
 */
public class ContractNotFoundException extends RuntimeException {

    /**
     * Constructs a new ContractNotFoundException with the specified detail message.
     *
     * @param message the detail message, which provides additional information about the exception.
     */
    public ContractNotFoundException(String message) {
        super(message);
    }
}
