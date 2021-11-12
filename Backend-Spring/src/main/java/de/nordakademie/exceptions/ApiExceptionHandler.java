package de.nordakademie.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {CreateFailedException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(CreateFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                                                           exception.getThrowable(),
                                                           exception.getHttpStatus(),
                                                           ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }
}
