package com.example.controller;

import com.example.exceptions.StorageException;
import com.example.exceptions.StorageFileNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Exception handler for exceptions thrown by service module.
 *
 * @author Evhen Malysh
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle bad requests.
     *
     * @param ex      handled exception
     * @param request web request
     * @return ResponseEntity with 400 HTTP status code
     */
    @ExceptionHandler({StorageException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected final ResponseEntity<Object> handleBadRequests(
        final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handle not found exceptions.
     *
     * @param ex      handled exception
     * @param request web request
     * @return ResponseEntity with 404 HTTP status code
     */
    @ExceptionHandler({StorageFileNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    protected final ResponseEntity<Object> handleNotFound(
        final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
            new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}