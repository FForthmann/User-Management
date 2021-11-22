package de.nordakademie.exceptions;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
/**
 * API exception which can be thrown.
 * These exceptions are the interface to the frontend for reporting failures.
 *
 * @author Fabian Forthmann
 */
public class ApiException {
    /**
     * The Exception Message.
     */
    private final String message;

    /**
     * The HTTP status of the processed request.
     */
    private final HttpStatus httpStatus;

    /**
     * Timestamp of the thrown Exception.
     */
    private final ZonedDateTime timestamp;

    /**
     * Instantiates a new API exception.
     *
     * @param message    the error message
     * @param httpStatus the HTTP status
     * @param timestamp  the current timestamp
     */
    public ApiException(final String message, final HttpStatus httpStatus, final ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    /**
     * Gets error message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets HTTP status.
     *
     *
     * @return the http status
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
