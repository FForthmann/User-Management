package de.nordakademie.exceptions;

import org.springframework.http.HttpStatus;
public class CreateFailedException extends Exception {
    private final String message;

    private final Throwable throwable;

    private final HttpStatus httpStatus;

    public CreateFailedException(final String message, final Throwable throwable, final HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Throwable getThrowable() {
        return throwable;
    }

}
