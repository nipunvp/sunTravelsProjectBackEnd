package com.example.demo_web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global Exception Handler for managing exceptions in the application.
 * This class is annotated with {@link ControllerAdvice} to handle exceptions globally for all controllers.
 * It defines specific handlers for {@link InvalidContractException} and {@link InvalidSearchException}.
 * The exception handlers catch the respective exceptions and return appropriate error responses with a BAD_REQUEST status.
 *
 * @author [Your Name]
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link InvalidContractException} by returning a {@link ResponseEntity}
     * with a BAD_REQUEST status and the exception message.
     *
     * @param ex the exception that was thrown.
     * @return a {@link ResponseEntity} with the exception message and HTTP status 400 (BAD_REQUEST).
     */
    @ExceptionHandler(InvalidContractException.class)
    public ResponseEntity<String> handleInvalidContractException(InvalidContractException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link InvalidSearchException} by returning a {@link ResponseEntity}
     * with a BAD_REQUEST status and the exception message.
     *
     * @param ex the exception that was thrown.
     * @return a {@link ResponseEntity} with the exception message and HTTP status 400 (BAD_REQUEST).
     */
    @ExceptionHandler(InvalidSearchException.class)
    public ResponseEntity<String> handleInvalidSearchException(InvalidSearchException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
