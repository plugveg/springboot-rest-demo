package org.setsuma.springboot.demorest.exceptions;

public class BannedBooksNotFoundException extends RuntimeException {

    public BannedBooksNotFoundException() {
        super();
    }

    public BannedBooksNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BannedBooksNotFoundException(final String message) {
        super(message);
    }

    public BannedBooksNotFoundException(final Throwable cause) {
        super(cause);
    }
}