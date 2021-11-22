package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import de.nordakademie.model.User;
/**
 * The interface for user service.
 *
 * @author Ridvan Cetin, Fabian Forthmann
 */
public interface UserService {
    /**
     * Creates user.
     *
     * @param user the user to be create
     * @return the created user
     */
    User createUser(User user);

    /**
     * Updates user.
     *
     * @param id the id of the user to be updated
     * @param user the user object which will be updating the current user
     */
    void updateUser(Long id, User user);

    /**
     * Deletes user by id.
     *
     * @param userId the id of the user who will be deleted
     */
    void deleteUserById(long userId);

    /**
     * Find all users.
     *
     * @return all users as a list
     */
    List<User> findAllUser();

    /**
     * Finds user by id.
     *
     * @param userId the user id
     * @return the user object which was searched for
     */
    Optional<User> findUserById(Long userId);
}
