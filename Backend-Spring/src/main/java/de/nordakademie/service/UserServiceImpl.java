package de.nordakademie.service;

import de.nordakademie.model.User;
import de.nordakademie.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository repository;




    @Override
    public User createUser(User user) {

        return repository.save(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(long userId) {
repository.deleteById(userId);
    }

    @Override
    public List<User> findAllUser() {

        return (List<User>) repository.findAll();
    }

    @Override
    public Optional<User> findeUserById(Long userId) {
        return repository.findById(userId);
    }

    @Inject
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}
