package de.nordakademie.exceptions;

import org.springframework.http.HttpStatus;

/**
 * The type Create failed exception.
 */
public class CreateFailedException extends Exception {
    /**
     * The Message.
     */
    private final String message;

    /**
     * The Http status.
     */
    private final HttpStatus httpStatus;

    /**
     * Instantiates a new Create failed exception.
     *
     * @param message    the message
     * @param httpStatus the http status
     */
    public CreateFailedException(final String message, final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Gets http status.
     *
     * @return the http status
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
