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

/**
 * The type User controller.
 */
@RestController
@RequestMapping(path = "/rest/user")
public class UserController {
    /**
     * The Service.
     */
    private UserService service;

    /**
     * Sets service.
     *
     * @param service the service
     */
    @Inject
    public void setService(UserService service) {
        this.service = service;
    }

    /**
     * Find all user list.
     *
     * @return the list
     */
    @GetMapping
    public List<User> findAllUser() {
        return service.findAllUser();
    }

    /**
     * Find user by id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws ReadFailedException the read failed exception
     */
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

    /**
     * Delete user response entity.
     *
     * @param userId the user id
     * @return the response entity
     * @throws DeleteFailedException the delete failed exception
     */
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

    /**
     * Create user response entity.
     *
     * @param user the user
     * @return the response entity
     * @throws CreateFailedException the create failed exception
     */
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

    /**
     * Update user response entity.
     *
     * @param id   the id
     * @param user the user
     * @return the response entity
     * @throws UpdateFailedException the update failed exception
     */
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
