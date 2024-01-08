package org.setsuma.springboot.demorest.exceptions;

public class BannedBooksIdMismatchException extends RuntimeException {

    public BannedBooksIdMismatchException() {
        super();
    }

    public BannedBooksIdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BannedBooksIdMismatchException(final String message) {
        super(message);
    }

    public BannedBooksIdMismatchException(final Throwable cause) {
        super(cause);
    }
}
