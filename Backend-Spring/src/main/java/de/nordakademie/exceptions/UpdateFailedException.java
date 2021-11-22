package de.nordakademie.exceptions;

import org.springframework.http.HttpStatus;

/**
 * The type Update failed exception.
 */
public class UpdateFailedException extends Exception {
    /**
     * The Message.
     */
    private final String message;

    /**
     * The Http status.
     */
    private final HttpStatus httpStatus;

    /**
     * Instantiates a new Update failed exception.
     *
     * @param message    the message
     * @param httpStatus the http status
     */
    public UpdateFailedException(final String message, final HttpStatus httpStatus) {
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
