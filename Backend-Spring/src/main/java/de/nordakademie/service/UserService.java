package de.nordakademie.service;

import de.nordakademie.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    void updateUser(User user);

    void deleteUserById(long userId);

    List<User> findAllUser();

    Optional<User> findeUserById(Long userId);

}
