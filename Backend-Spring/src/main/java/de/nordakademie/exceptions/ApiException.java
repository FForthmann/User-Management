package de.nordakademie.exceptions;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
public class ApiException extends Exception {
    private final String message;

    private final Throwable throwable;

    private final HttpStatus httpStatus;

    private final ZonedDateTime timestamp;

    public ApiException(final String message, final Throwable throwable, final HttpStatus httpStatus, final ZonedDateTime timestamp) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
