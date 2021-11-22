package de.nordakademie.exceptions;

import org.springframework.http.HttpStatus;
/**
 * Exception class for failed deletion attempts.
 *
 * @author Ridvan Cetin, Fabian Forthmann
 */
public class DeleteFailedException extends Exception {
    /**
     * The error message.
     */
    private final String message;

    /**
     * The HTTP status of the error.
     */
    private final HttpStatus httpStatus;

    /**
     * Instantiates a new DeleteFailed exception.
     *
     * @param message    the error message
     * @param httpStatus the HTTP status of the error
     */
    public DeleteFailedException(final String message, final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    /**
     * Gets the HTTP status.
     *
     * @return the HTTP status of the error
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
