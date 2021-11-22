package de.nordakademie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Api exception handler.
 */
@ControllerAdvice
public class ApiExceptionHandler {
    /**
     * Handle create failed exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {CreateFailedException.class})
    public ResponseEntity<Object> handleCreateFailedException(CreateFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                exception.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    /**
     * Handle read failed exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {DeleteFailedException.class})
    public ResponseEntity<Object> handleReadFailedException(DeleteFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                exception.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    /**
     * Handle read failed exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {UpdateFailedException.class})
    public ResponseEntity<Object> handleReadFailedException(UpdateFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                exception.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    /**
     * Handle read failed exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {ReadFailedException.class})
    public ResponseEntity<Object> handleReadFailedException(ReadFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                exception.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    /**
     * Handle validation exceptions response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> list = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            list.add(errorMessage);
        });
        final ApiException apiException = new ApiException(list.get(0), HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
