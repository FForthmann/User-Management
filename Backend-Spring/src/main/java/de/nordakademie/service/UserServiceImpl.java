package de.nordakademie.service;

import de.nordakademie.model.User;
import de.nordakademie.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserRepository repository;




    @Override
    public User createUser(User user) {

        return repository.save(user);
    }

    @Override
    public void updateUser(Long id, User updateUser) {
    Optional<User> persistentUser = repository.findById(id);

        persistentUser.get().setBirthday(updateUser.getBirthday());
        persistentUser.get().setCancellationDate(updateUser.getCancellationDate());
        persistentUser.get().setEntryDate(updateUser.getEntryDate());
        persistentUser.get().setAccountDetails(updateUser.getAccountDetails());
        persistentUser.get().setFamilyId(updateUser.getFamilyId());
        persistentUser.get().getName().setFirstName(updateUser.getName().getFirstName());
        persistentUser.get().getAddress().setHouseNumber(updateUser.getAddress().getHouseNumber());
        persistentUser.get().setMemberType(updateUser.getMemberType());
        persistentUser.get().getAddress().setPostalCode(updateUser.getAddress().getPostalCode());
        persistentUser.get().getName().setLastName(updateUser.getName().getLastName());
        persistentUser.get().getAddress().setStreet(updateUser.getAddress().getStreet());

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
