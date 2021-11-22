package de.nordakademie.service;

import de.nordakademie.model.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    User createUser(User user);

    /**
     * Update user.
     *
     * @param id   the id
     * @param user the user
     */
    void updateUser(Long id, User user);

    /**
     * Delete user by id.
     *
     * @param userId the user id
     */
    void deleteUserById(long userId);

    /**
     * Find all user list.
     *
     * @return the list
     */
    List<User> findAllUser();

    /**
     * Find user by id optional.
     *
     * @param userId the user id
     * @return the optional
     */
    Optional<User> findUserById(Long userId);

}
