package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import de.nordakademie.model.User;
public interface UserService {
    User createUser(User user);

    void updateUser(Long id, User user);

    void deleteUserById(long userId);

    List<User> findAllUser();

    Optional<User> findUserById(Long userId);

}
