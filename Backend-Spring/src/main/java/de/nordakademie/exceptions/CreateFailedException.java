package de.nordakademie.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception class for failed creation attempts.
 *
 * @author Ridvan Cetin, Fabian Forthmann
 */
public class CreateFailedException extends Exception {
    /**
     * The error message.
     */
    private final String message;

    /**
     * The HTTPS status of the error.
     */
    private final HttpStatus httpStatus;

    /**
     * Instantiates a new CreateFailed exception.
     *
     * @param message    the error message
     * @param httpStatus the HTTP status of the error
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
     * Gets the HTTP status.
     *
     * @return the HTTP status of the error
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
