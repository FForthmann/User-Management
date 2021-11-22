package de.nordakademie.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * The type Api exception.
 */
public class ApiException {
    /**
     * The Message.
     */
    private final String message;

    /**
     * The Http status.
     */
    private final HttpStatus httpStatus;

    /**
     * The Timestamp.
     */
    private final ZonedDateTime timestamp;

    /**
     * Instantiates a new Api exception.
     *
     * @param message    the message
     * @param httpStatus the http status
     * @param timestamp  the timestamp
     */
    public ApiException(final String message, final HttpStatus httpStatus, final ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
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

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
