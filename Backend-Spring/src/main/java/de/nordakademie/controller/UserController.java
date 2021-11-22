package de.nordakademie.controller;

import de.nordakademie.exceptions.CreateFailedException;
import de.nordakademie.exceptions.DeleteFailedException;
import de.nordakademie.exceptions.ReadFailedException;
import de.nordakademie.exceptions.UpdateFailedException;
import de.nordakademie.model.User;
import de.nordakademie.service.UserService;
import de.nordakademie.util.ExceptionMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rest/user")
public class UserController {
    private UserService service;

    @Inject
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> findAllUser() {
        return service.findAllUser();
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(
            @PathVariable("id")
                    Long userId) throws ReadFailedException {
        try {
            return service.findUserById(userId);
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            throw new ReadFailedException(ExceptionMessages.USER_READ_FAILED, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(
            @PathVariable("id")
                    Long userId) throws DeleteFailedException {
        try {
            service.deleteUserById(userId);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            throw new DeleteFailedException(ExceptionMessages.USER_DELETE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException ex) {
            throw new DeleteFailedException(ExceptionMessages.USER_NOT_FOUND_WHEN_DELETE, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid
            @RequestBody
                    User user) throws CreateFailedException {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createUser(user));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            throw new CreateFailedException(ExceptionMessages.USER_CREATION_FAILED + " " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id")
                    Long id,
            @Valid
            @RequestBody
                    User user) throws UpdateFailedException {
        try {
            service.updateUser(id, user);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (IllegalArgumentException ex) {
            throw new UpdateFailedException(ExceptionMessages.USER_UPDATE_ILLEGAL_ARGUMENT + " " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException ex) {
            throw new UpdateFailedException(ExceptionMessages.USER_NOT_FOUND_WHEN_UPDATE, HttpStatus.NOT_FOUND);
        }

    }
}
