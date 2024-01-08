package org.setsuma.springboot.demorest.controllers;

import org.setsuma.springboot.demorest.exceptions.BookIdMismatchException;
import org.setsuma.springboot.demorest.exceptions.BookNotFoundException;
import org.setsuma.springboot.demorest.exceptions.BannedBooksIdMismatchException;
import org.setsuma.springboot.demorest.exceptions.BannedBooksNotFoundException;
import org.setsuma.springboot.demorest.exceptions.BannedBooksException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public RestExceptionHandler() {
        super();
    }

    @ExceptionHandler(BookNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Book not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BannedBooksNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound1(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "BannedBook not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BannedBooksException.class)
    protected ResponseEntity<Object> handleNotFound2(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Livre Interdit", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({
      BannedBooksIdMismatchException.class,
      BookIdMismatchException.class,
      ConstraintViolationException.class,
      DataIntegrityViolationException.class
    })
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex
          .getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}