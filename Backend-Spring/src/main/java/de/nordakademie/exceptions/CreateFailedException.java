package de.nordakademie.exceptions;

import org.springframework.http.HttpStatus;

public class CreateFailedException extends Exception {
    private final String message;

    private final HttpStatus httpStatus;

    public CreateFailedException(final String message, final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
