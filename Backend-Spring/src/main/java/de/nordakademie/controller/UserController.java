package de.nordakademie.controller;

import de.nordakademie.model.User;
import de.nordakademie.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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
    public Optional<User> findUserById(@PathVariable("id") Long userId) {

        return service.findeUserById(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long userId){
        service.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<User> createMemberType(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateMemberType(@PathVariable("id") Long id, @RequestBody User user){
        service.updateUser(id, user);
        return ResponseEntity.ok().build();
    }
}
