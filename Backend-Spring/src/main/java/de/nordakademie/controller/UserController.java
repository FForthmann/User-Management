package de.nordakademie.controller;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.nordakademie.exceptions.CreateFailedException;
import de.nordakademie.exceptions.DeleteFailedException;
import de.nordakademie.exceptions.ReadFailedException;
import de.nordakademie.exceptions.UpdateFailedException;
import de.nordakademie.model.User;
import de.nordakademie.service.UserService;
import de.nordakademie.util.ExceptionMessages;
/**
 * The Spring Controller for User
 *
 * @author Ridvan Cetin & Fabian Forthmann
 */
@RestController
@RequestMapping(path = "/rest/user")
public class UserController {
    /**
     * The Service for processing user requests.
     */
    private UserService service;

    /**
     * Sets the service.
     *
     * @param service the service
     */
    @Inject
    public void setService(UserService service) {
        this.service = service;
    }

    /**
     * Researches all users and returns them as a list.
     *
     * @return list of all users
     */
    @GetMapping
    public List<User> findAllUser() {
        return service.findAllUser();
    }

    /**
     * Searches a user by its id and returns it.
     *
     * @param userId the user id
     * @return the user or an empty optional object
     * @throws ReadFailedException finding the user failed due to a specific failure
     */
    @GetMapping("/{id}")
    public Optional<User> findUserById(
            @PathVariable("id")
                    Long userId) throws ReadFailedException {
        try {
            return service.findUserById(userId);
        } catch ( EntityNotFoundException ex ) {
            ex.printStackTrace();
            throw new ReadFailedException(ExceptionMessages.USER_READ_FAILED, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a user.
     *
     * @param user the user object
     * @return the created user as a response entity
     * @throws CreateFailedException creating the user failed due to a specific failure
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
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new CreateFailedException(ExceptionMessages.USER_CREATION_FAILED + " " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a user by its id.
     *
     * @param userId the user id
     * @return an entity object with an HTTP status
     * @throws DeleteFailedException deleting the user failed due to a specific failure
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
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new DeleteFailedException(ExceptionMessages.USER_DELETE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch ( EntityNotFoundException ex ) {
            throw new DeleteFailedException(ExceptionMessages.USER_NOT_FOUND_WHEN_DELETE, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates a user.
     *
     * @param id the user id
     * @param user the user object
     * @return the updated user as a response entity
     * @throws UpdateFailedException updating the user failed due to a specific failure
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
        } catch ( IllegalArgumentException ex ) {
            throw new UpdateFailedException(ExceptionMessages.USER_UPDATE_ILLEGAL_ARGUMENT + " " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch ( EntityNotFoundException ex ) {
            throw new UpdateFailedException(ExceptionMessages.USER_NOT_FOUND_WHEN_UPDATE, HttpStatus.NOT_FOUND);
        }
    }
}
