package de.nordakademie.service;

import de.nordakademie.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    void updateUser(Long id, User user);

    void deleteUserById(long userId);

    List<User> findAllUser();

    Optional<User> findUserById(Long userId);

}
