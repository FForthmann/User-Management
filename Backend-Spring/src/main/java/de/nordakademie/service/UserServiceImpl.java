package de.nordakademie.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import de.nordakademie.model.User;
import de.nordakademie.repository.MemberTypeRepository;
import de.nordakademie.repository.PaymentsRepository;
import de.nordakademie.repository.PostcodeRepository;
import de.nordakademie.repository.UserRepository;
import de.nordakademie.util.ApiMessages;
import de.nordakademie.util.ExceptionMessages;
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

        validateInputUserForUpdateAndInsert(createUser);

        // Validation if MemberType exists in DB
        if (!existsMemberTypeInDB(createUser)) {
            throw new IllegalArgumentException(ApiMessages.MSG_MEMBERTYPE_NOT_IN_DB + createUser
                    .getMemberType()
                    .getDescription());
        }

        // If Postal Code doesn't exist in DB, here it will be created
        if (!existsPostalCodeInDB(createUser)) {
            this.postcodeRepository.save(createUser
                                                 .getAddress()
                                                 .getPostalCode());
        }

        return repository.save(createUser);
    }

    @Override
    public void updateUser(Long id, User updateUser) {

        validateInputUserForUpdateAndInsert(updateUser);

        Optional<User> persistentUser = repository.findById(id);
        if (!persistentUser.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.MSG_ENTITY_NOT_EXISTS);
        }

        persistentUser
                .get()
                .setBirthday(updateUser.getBirthday());
        persistentUser
                .get()
                .setCancellationDate(updateUser.getCancellationDate());
        persistentUser
                .get()
                .setEntryDate(updateUser.getEntryDate());
        persistentUser
                .get()
                .setFamilyId(updateUser.getFamilyId());
        persistentUser
                .get()
                .getName()
                .setFirstName(updateUser
                                      .getName()
                                      .getFirstName());
        persistentUser
                .get()
                .getAddress()
                .setHouseNumber(updateUser
                                        .getAddress()
                                        .getHouseNumber());
        persistentUser
                .get()
                .setMemberType(updateUser.getMemberType());
        persistentUser
                .get()
                .getAddress()
                .setPostalCode(updateUser
                                       .getAddress()
                                       .getPostalCode());
        persistentUser
                .get()
                .getName()
                .setLastName(updateUser
                                     .getName()
                                     .getLastName());
        persistentUser
                .get()
                .getAddress()
                .setStreet(updateUser
                                   .getAddress()
                                   .getStreet());
    }

    @Override
    public void deleteUserById(long userId) {

        Optional<User> user = repository.findById(userId);
        if (!user.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.MSG_ENTITY_NOT_EXISTS);
        }

        // ToDo fafor: Change UserId in Payments to null
        if (paymentsRepository.existsUserInPayments(userId)) {

        }
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
        return this.memberTypeRepository.existsById(createUser
                                                            .getMemberType()
                                                            .getDescription());
    }

    private boolean existsPostalCodeInDB(User createUser) {
        return this.postcodeRepository.existsById(createUser
                                                          .getAddress()
                                                          .getPostalCode()
                                                          .getPostcode());
    }

    private void validateInputUserForUpdateAndInsert(User user) {
        if (!isStreetOnlyText(user)) {
            throw new IllegalArgumentException(ExceptionMessages.USER_STREET_NOT_TEXT);
        }

        if (!isFirstNameOnlyText(user)) {
            throw new IllegalArgumentException(ExceptionMessages.USER_FIRST_NAME_NOT_TEXT);
        }

        if (!isLastNameOnlyText(user)) {
            throw new IllegalArgumentException(ExceptionMessages.USER_LAST_NAME_NOT_TEXT);
        }

        if (!isBirthdayBeforeEntryDateAndNow(user)) {
            throw new IllegalArgumentException(ExceptionMessages.USER_BIRTHDAY_BEFORE_ENTRY_DATE_OR_IN_FUTURE);
        }

        if (!isEntryDateBeforeCancellationDate(user)) {
            throw new IllegalArgumentException(ExceptionMessages.USER_ENTRY_DATE_BEFORE_CANCELLATION_DATE);
        }
    }

    private boolean isStreetOnlyText(User user) {
        return user
                .getAddress()
                .getStreet()
                .matches("[a-zA-Z\\s'\"]+");
    }

    private boolean isFirstNameOnlyText(User user) {
        return user
                .getName()
                .getFirstName()
                .matches("[a-zA-Z\\s'\"]+");
    }

    private boolean isLastNameOnlyText(User user) {
        return user
                .getName()
                .getLastName()
                .matches("[a-zA-Z\\s'\"]+");
    }

    private boolean isBirthdayBeforeEntryDateAndNow(User user) {
        return user
                .getBirthday()
                .isBefore(user.getEntryDate()) && user
                .getBirthday()
                .isBefore(LocalDate.now());
    }

    private boolean isEntryDateBeforeCancellationDate(User user) {
        if (user.getCancellationDate() == null) {
            return true;
        }
        return user
                .getEntryDate()
                .isBefore(user.getCancellationDate());
    }
}
