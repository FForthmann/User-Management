package de.nordakademie.exceptions;

import org.springframework.http.HttpStatus;
public class UpdateFailedException extends Exception {
    private final String message;

    private final Throwable throwable;

    private final HttpStatus httpStatus;

    public UpdateFailedException(final String message, final Throwable throwable, final HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
