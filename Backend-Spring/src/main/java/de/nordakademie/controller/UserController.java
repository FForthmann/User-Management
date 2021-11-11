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
import de.nordakademie.model.User;
import de.nordakademie.service.UserService;
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
                    Long userId) {
        return service.findUserById(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(
            @PathVariable("id")
                    Long userId) {
        try {
            service.deleteUserById(userId);
            return ResponseEntity
                    .ok()
                    .build();
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody
                    User user) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createUser(user));
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateMemberType(
            @PathVariable("id")
                    Long id,
            @RequestBody
                    User user) {
        try {
            service.updateUser(id, user);
            return ResponseEntity
                    .ok()
                    .build();
        } catch ( IllegalArgumentException ex ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch ( EntityNotFoundException ex ) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }
}
