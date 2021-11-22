package de.nordakademie.exceptions;

import org.springframework.http.HttpStatus;

/**
 * The type Delete failed exception.
 */
public class DeleteFailedException extends Exception {
    /**
     * The Message.
     */
    private final String message;

    /**
     * The Http status.
     */
    private final HttpStatus httpStatus;

    /**
     * Instantiates a new Delete failed exception.
     *
     * @param message    the message
     * @param httpStatus the http status
     */
    public DeleteFailedException(final String message, final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    /**
     * Gets http status.
     *
     * @return the http status
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
