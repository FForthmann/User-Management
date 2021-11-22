package de.nordakademie.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import de.nordakademie.model.MemberType;
import de.nordakademie.model.Payments;
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

    private PaymentsService paymentsService;

    private MemberTypeService memberTypeService;

    private PostcodeService postcodeService;

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

        createUser = setLeavingDate(createUser);

        validateInputUserForUpdateAndInsert(createUser);

        // Check if Membertype Teenage is valid with the inserted age
        if (createUser
                .getMemberType()
                .getDescription()
                .equals("Jugendlich") && checkUserUnderEighteen(createUser)) {
            throw new IllegalArgumentException("Der Benutzer ist bereits erwachsen und kann kein Jugendkonto einrichten.");
        }

        // Validation if MemberType exists in DB
        if (!existsMemberTypeInDB(createUser)) {
            throw new IllegalArgumentException(ApiMessages.MEMBERTYPE_NOT_IN_DB + createUser
                    .getMemberType()
                    .getDescription());
        }

        // If Postal Code doesn't exist in DB, here it will be created
        if (!existsPostalCodeInDB(createUser)) {
            this.postcodeService.createPostcode(createUser
                                                        .getAddress()
                                                        .getPostalCode());
        }

        // Create Payment for User
        User savedUser = repository.save(createUser);
        createPaymentByUser(savedUser);
        return savedUser;
    }

    @Override
    public void updateUser(Long id, User updateUser) {

        validateInputUserForUpdateAndInsert(updateUser);

        Optional<User> persistentUser = repository.findById(id);
        if (!persistentUser.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.ENTITY_NOT_EXISTS);
        }

        computeAndInsertLeavingDate(updateUser, persistentUser);

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
        persistentUser
                .get()
                .setMemberTypeChange(updateUser.getMemberTypeChange());
        persistentUser
                .get()
                .setBankAccountDetails(updateUser.getBankAccountDetails());

        updatePaymentsByUser(id,updateUser);
    }

    private void updatePaymentsByUser(long id, User updateUser) {
        long invoiceNumber = paymentsService.findPaymentsByUserId(id, LocalDate.now().getYear());
        Optional<Payments> payments = paymentsService.findPaymentsById(invoiceNumber);
        if(payments.isPresent()){
          payments.get().setBankAccountDetails(updateUser.getBankAccountDetails());
            paymentsService.updatePayments(invoiceNumber, payments.get());
        }

    }

    @Override
    public void deleteUserById(long userId) {

        Optional<User> user = repository.findById(userId);
        if (!user.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.ENTITY_NOT_EXISTS);
        }

        if (paymentsRepository.existsUserInPayments(userId)) {
            paymentsRepository.updateUserIdToNull(userId);
        }

        // familyMember
        if (repository.existsFamilyIdByUserId(userId)) {
            repository.updateFamilyIdToNullByUserId(userId);
        }

        repository.deleteById(userId);
    }

    @Override
    public List<User> findAllUser() {

        LocalDate localDate = LocalDate.now();
        if (localDate
                .getMonth()
                .getValue() == 01 && localDate.getDayOfMonth() == 01) {
            List<User> list = (List<User>) repository.findAll();
            // All users deleted who are no longer members
            list
                    .stream()
                    .filter(user -> user.getLeavingDate() != null && user
                            .getLeavingDate()
                            .isEqual(localDate))
                    .forEach(user -> deleteUserById(user.getUserId()));

            List<User> listMitMitgliedern = (List<User>) repository.findAll();
            for ( User user :
                    listMitMitgliedern ) {
                if (user.getMemberTypeChange() != null) {
                    user.setMemberType(user.getMemberTypeChange());
                    user.setMemberTypeChange(null);
                    updateUser(user.getUserId(), user);
                }
                // check if there is a payment for this year for this user
                if (!paymentsService.existsUserInPaymentsForThisYear(user.getUserId(), localDate.getYear())) {
                    if (user
                            .getMemberType()
                            .getDescription()
                            .equals("Jugendlich") && checkUserUnderEighteen(user)) {
                        throw new IllegalArgumentException("Der Benutzer ist bereits erwachsen und kann kein Jugendkonto einrichten.");
                    }
                    createPaymentByUser(user);
                }
            }
        }
        return (List<User>) repository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return repository.findById(userId);
    }

    @Inject
    public void setPaymentsService(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @Inject
    public void setMemberTypeService(MemberTypeService memberTypeService) {
        this.memberTypeService = memberTypeService;
    }

    @Inject
    public void setPostcodeService(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    private void createPaymentByUser(User savedUser) {
        Payments payments = new Payments();
        payments.setBankAccountDetails(savedUser.getBankAccountDetails());
        payments.setUserId(savedUser);
        payments.setYear(LocalDate
                                 .now()
                                 .getYear());
        payments.setCountStatus(Boolean.FALSE);
        payments.setAmount(evaluateAmountForUser(savedUser));
        paymentsService.createPayments(payments);
    }

    private Double evaluateAmountForUser(User createUser) {
        Double amountResult = 0.0;
        Optional<MemberType> a = memberTypeService.findMemberTypeById(createUser
                                                                              .getMemberType()
                                                                              .getDescription());
        if (a.isPresent()) {
            if (createUser.getFamilyId() != null) {
                amountResult -= 3;
            } else {
                amountResult += a
                        .get()
                        .getAmount();
            }
        }
        return amountResult;
    }

    private boolean checkUserUnderEighteen(User createUser) {
        Period period = Period.between(createUser.getBirthday(), LocalDate.now());
        if (period.getYears() >= 18) {
            return true;
        } else {
            return false;
        }
    }

    private boolean check(User createUser) {
        Period period = Period.between(createUser.getBirthday(), LocalDate.now());
        return period.getYears() >= 18;
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

    private void computeAndInsertLeavingDate(final User updateUser, final Optional<User> persistentUser) {
        if (updateUser.getCancellationDate() != null) {
            LocalDate regularLeavingDate = LocalDate.of(updateUser
                                                                .getCancellationDate()
                                                                .plusYears(1)
                                                                .getYear(), 1, 1);
            if (updateUser
                    .getCancellationDate()
                    .plusMonths(3)
                    .isBefore(regularLeavingDate)) {
                persistentUser
                        .get()
                        .setLeavingDate(regularLeavingDate);
            } else {
                persistentUser
                        .get()
                        .setLeavingDate(regularLeavingDate.plusYears(1));
            }
        }
    }

    private User setLeavingDate(final User createUser) {
        if (createUser.getCancellationDate() != null) {
            LocalDate regularLeavingDate = LocalDate.of(createUser
                                                                .getCancellationDate()
                                                                .plusYears(1)
                                                                .getYear(), 1, 1);
            if (createUser
                    .getCancellationDate()
                    .plusMonths(3)
                    .isBefore(regularLeavingDate)) {
                createUser.setLeavingDate(regularLeavingDate);
                return createUser;
            } else {
                createUser.setLeavingDate(regularLeavingDate.plusYears(1));
                return createUser;
            }
        } else {
            return createUser;
        }
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
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
    }

    private boolean isFirstNameOnlyText(User user) {
        return user
                .getName()
                .getFirstName()
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
    }

    private boolean isLastNameOnlyText(User user) {
        return user
                .getName()
                .getLastName()
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
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
