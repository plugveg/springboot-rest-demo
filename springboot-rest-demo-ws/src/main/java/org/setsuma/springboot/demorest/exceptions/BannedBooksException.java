package org.setsuma.springboot.demorest.exceptions;

public class BannedBooksException extends RuntimeException {

    public BannedBooksException() {
        super("Livre Interdit");
    }

    public BannedBooksException(final String message, final Throwable cause) {
        super("Livre Interdit" + message, cause);
    }

    public BannedBooksException(final String message) {
        super("Livre Interdit" + message);
    }

    public BannedBooksException(final Throwable cause) {
        super("Livre Interdit", cause);
    }
}
