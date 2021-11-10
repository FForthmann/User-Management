package de.nordakademie.service;

import de.nordakademie.model.User;
import de.nordakademie.repository.MemberTypeRepository;
import de.nordakademie.repository.PaymentsRepository;
import de.nordakademie.repository.PostcodeRepository;
import de.nordakademie.repository.UserRepository;
import de.nordakademie.util.ApiMessages;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private MemberTypeRepository memberTypeRepository;
    private PostcodeRepository postcodeRepository;
    private PaymentsRepository paymentsRepository;
    private UserRepository repository;

    @Inject
    public void setPaymentsRepository(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @Inject
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Inject
    public void setPostcodeRepository(PostcodeRepository postcodeRepository) {
        this.postcodeRepository = postcodeRepository;
    }

    @Inject
    public void setMemberTypeRepository(MemberTypeRepository memberTypeRepository) {
        this.memberTypeRepository = memberTypeRepository;
    }

    @Override
    public User createUser(User createUser) {

        // Die Validierung beinhaltet, dass alle Pflichtfelder auch gefüllt sind
        if (checkMandatoryAttributesAreNotNull(createUser)) {
            throw new IllegalArgumentException(ApiMessages.MSG_NULL);
        }

        // Validation if MemberType exists in DB
        if (!existsMemberTypeInDB(createUser)) {
            throw new IllegalArgumentException(ApiMessages.MSG_MEMBERTYPE_NOT_IN_DB +  createUser.getMemberType().getDescription());
        }

        // If Postal Code doesn't exist in DB, here it will be created
        if (!existsPostalCodeInDB(createUser)) {
            this.postcodeRepository.save(createUser.getAddress().getPostalCode());
        }

        return repository.save(createUser);
    }

    @Override
    public void updateUser(Long id, User updateUser) {
        // Validation
        if (checkMandatoryAttributesAreNotNull(updateUser)) {
            throw new IllegalArgumentException(ApiMessages.MSG_NULL);
        }

        Optional<User> persistentUser = repository.findById(id);
        if (!persistentUser.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.MSG_ENTITY_NOT_EXISTS);
        }

        persistentUser.get().setBirthday(updateUser.getBirthday());
        persistentUser.get().setCancellationDate(updateUser.getCancellationDate());
        persistentUser.get().setEntryDate(updateUser.getEntryDate());
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

        Optional<User> user = repository.findById(userId);
        if (!user.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.MSG_ENTITY_NOT_EXISTS);
        }

        // Was gehört zum Löschen alles dazu
        // Wenn User in Payments vorhanden, dann muss auch Payments gelöscht werden
        if (paymentsRepository.existsUserInPayments(userId)){

        }

        // Darf dieser überhaupt gelöscht werden?


        repository.deleteById(userId);
    }

    @Override
    public List<User> findAllUser() {
        return (List<User>) repository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return repository.findById(userId);
    }

    private boolean existsMemberTypeInDB(User createUser) {
        return this.memberTypeRepository.existsById(createUser.getMemberType().getDescription());
    }

    private boolean existsPostalCodeInDB(User createUser) {
        return this.postcodeRepository.existsById(createUser.getAddress().getPostalCode().getPostcode());
    }

    private boolean checkMandatoryAttributesAreNotNull(User createUser) {
        return isNull(createUser.getAddress().getHouseNumber(), createUser.getAddress().getStreet(), createUser.getAddress().getPostalCode().getPostcode(), createUser.getAddress().getPostalCode().getLocation(), createUser.getBirthday(), createUser.getEntryDate(), createUser.getMemberType().getDescription(), createUser.getName().getFirstName(), createUser.getName().getLastName());
    }

    private boolean isNull(Object... strArr) {
        for (Object st : strArr) {
            if (st == null)
                return true;
        }
        return false;
    }


}
