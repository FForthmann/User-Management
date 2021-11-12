package de.nordakademie.controller;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
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
        } catch ( EntityNotFoundException ex ) {
            ex.printStackTrace();
            throw new ReadFailedException(ExceptionMessages.USER_READ_FAILED, ex, HttpStatus.NOT_FOUND);
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
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new DeleteFailedException(ExceptionMessages.USER_DELETE_ILLEGAL_ARGUMENT, ex, HttpStatus.BAD_REQUEST);
        } catch ( EntityNotFoundException ex ) {
            throw new DeleteFailedException(ExceptionMessages.USER_NOT_FOUND_WHEN_DELETE, ex, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody
                    User user) throws CreateFailedException {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createUser(user));
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new CreateFailedException(ExceptionMessages.USER_CREATION_FAILED, ex, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateMemberType(
            @PathVariable("id")
                    Long id,
            @RequestBody
                    User user) throws UpdateFailedException {
        try {
            service.updateUser(id, user);
            return ResponseEntity
                    .ok()
                    .build();
        } catch ( IllegalArgumentException ex ) {
            throw new UpdateFailedException(ExceptionMessages.USER_UPDATE_ILLEGAL_ARGUMENT, ex, HttpStatus.BAD_REQUEST);
        } catch ( EntityNotFoundException ex ) {
            throw new UpdateFailedException(ExceptionMessages.USER_NOT_FOUND_WHEN_UPDATE, ex, HttpStatus.NOT_FOUND);
        }

    }
}
