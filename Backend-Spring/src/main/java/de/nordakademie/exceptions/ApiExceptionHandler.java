package de.nordakademie.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> list = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            list.add(errorMessage);
        });
        final ApiException apiException = new ApiException(list.get(0), null, null,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
