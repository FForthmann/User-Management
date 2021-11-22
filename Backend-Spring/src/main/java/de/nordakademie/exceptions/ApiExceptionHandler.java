package de.nordakademie.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * API exception handler catches specifically thrown exceptions and returns valid error JSONs to the user.
 *
 * @author Ridvan Cetin, Fabian Forthmann
 */
@ControllerAdvice
public class ApiExceptionHandler {
    /**
     * Handles CreateFailedExceptions.
     *
     * @param exception exception for failed creation attempts
     * @return final error message as ResponseEntity
     */
    @ExceptionHandler(value = {CreateFailedException.class})
    public ResponseEntity<Object> handleCreateFailedException(CreateFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                                                           exception.getHttpStatus(),
                                                           ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    /**
     * Handles DeleteFailedExceptions.
     *
     * @param exception exception for failed deletion attempts
     * @return final error message as ResponseEntity
     */
    @ExceptionHandler(value = {DeleteFailedException.class})
    public ResponseEntity<Object> handleReadFailedException(DeleteFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                                                           exception.getHttpStatus(),
                                                           ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    /**
     * Handles UpdateFailedExceptions.
     *
     * @param exception exception for failed update attempts
     * @return final error message as ResponseEntity
     */
    @ExceptionHandler(value = {UpdateFailedException.class})
    public ResponseEntity<Object> handleReadFailedException(UpdateFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                                                           exception.getHttpStatus(),
                                                           ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    /**
     * Handles ReadFailedExceptions.
     *
     * @param exception exception for failed read attempts
     * @return final error message as ResponseEntity
     */
    @ExceptionHandler(value = {ReadFailedException.class})
    public ResponseEntity<Object> handleReadFailedException(ReadFailedException exception) {
        final ApiException apiException = new ApiException(exception.getMessage(),
                                                           exception.getHttpStatus(),
                                                           ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, exception.getHttpStatus());
    }

    /**
     * Handles MethodArgumentNotValidException.
     *
     * @param exception exception for failed validations
     * @return final error message as ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> list = new ArrayList<>();
        exception
                .getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    list.add(errorMessage);
                });
        final ApiException apiException = new ApiException(list.get(0), HttpStatus.BAD_REQUEST,
                                                           ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
