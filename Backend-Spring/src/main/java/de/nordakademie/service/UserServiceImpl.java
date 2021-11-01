package de.nordakademie.service;

import de.nordakademie.model.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(long userId) {

    }

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public Optional<User> findeUserById(Long userId) {
        return Optional.empty();
    }
}
