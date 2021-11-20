package de.nordakademie.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {CreateFailedException.class})
    public ResponseEntity<Object> handleCreateFailedException(CreateFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                                                           exception.getHttpStatus(),
                                                           ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    @ExceptionHandler(value = {DeleteFailedException.class})
    public ResponseEntity<Object> handleReadFailedException(DeleteFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                                                           exception.getHttpStatus(),
                                                           ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    @ExceptionHandler(value = {UpdateFailedException.class})
    public ResponseEntity<Object> handleReadFailedException(UpdateFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                                                           exception.getHttpStatus(),
                                                           ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    @ExceptionHandler(value = {ReadFailedException.class})
    public ResponseEntity<Object> handleReadFailedException(ReadFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                                                           exception.getHttpStatus(),
                                                           ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }
}
