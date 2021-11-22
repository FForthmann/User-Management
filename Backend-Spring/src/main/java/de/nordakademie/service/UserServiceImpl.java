package de.nordakademie.service;

import de.nordakademie.model.MemberType;
import de.nordakademie.model.Payments;
import de.nordakademie.model.User;
import de.nordakademie.repository.MemberTypeRepository;
import de.nordakademie.repository.PaymentsRepository;
import de.nordakademie.repository.PostcodeRepository;
import de.nordakademie.repository.UserRepository;
import de.nordakademie.util.ApiMessages;
import de.nordakademie.util.ExceptionMessages;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

/**
 * The type User service.
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {
    /**
     * The Member type repository.
     */
    private MemberTypeRepository memberTypeRepository;

    /**
     * The Postcode repository.
     */
    private PostcodeRepository postcodeRepository;

    /**
     * The Payments repository.
     */
    private PaymentsRepository paymentsRepository;

    /**
     * The Repository.
     */
    private UserRepository repository;

    /**
     * The Payments service.
     */
    private PaymentsService paymentsService;

    /**
     * The Member type service.
     */
    private MemberTypeService memberTypeService;

    /**
     * The Postcode service.
     */
    private PostcodeService postcodeService;

    /**
     * Sets payments repository.
     *
     * @param paymentsRepository the payments repository
     */
    @Inject
    public void setPaymentsRepository(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    /**
     * Sets repository.
     *
     * @param repository the repository
     */
    @Inject
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Sets postcode repository.
     *
     * @param postcodeRepository the postcode repository
     */
    @Inject
    public void setPostcodeRepository(PostcodeRepository postcodeRepository) {
        this.postcodeRepository = postcodeRepository;
    }

    /**
     * Sets member type repository.
     *
     * @param memberTypeRepository the member type repository
     */
    @Inject
    public void setMemberTypeRepository(MemberTypeRepository memberTypeRepository) {
        this.memberTypeRepository = memberTypeRepository;
    }

    @Override
    public User createUser(User createUser) {

        createUser = setLeavingDate(createUser);

        validateInputUserForUpdateAndInsert(createUser);

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

        // Evaluate ActualAmount
        createUser.setActualAmount(evaluateAmountForUser(createUser));

        // Create Payment for User
        User savedUser = repository.save(createUser);

        checkUserIsFamilyUser(savedUser.getUserId(), savedUser);

        createPaymentByUser(savedUser);
        return savedUser;
    }

    @Override
    public void updateUser(Long id, User updateUser) {

        validateInputUserForUpdateAndInsert(updateUser);

        checkUserIsFamilyUser(id, updateUser);

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
        persistentUser.get().setActualAmount(evaluateAmountForUser(updateUser));

        this.postcodeService.updatePostcode(updateUser.getAddress().getPostalCode().getPostcode(), updateUser.getAddress().getPostalCode());

        updatePaymentsByUser(id, updateUser);
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
                .getValue() == 12 && localDate.getDayOfMonth() == 31) {
            List<User> list = (List<User>) repository.findAll();
            // All users deleted who are no longer members
            list.stream().filter(user -> user.getLeavingDate() != null && user
                            .getLeavingDate()
                            .isEqual(localDate))
                    .forEach(user -> deleteUserById(user.getUserId()));
        }
        if (localDate
                .getMonth()
                .getValue() == 1 && localDate.getDayOfMonth() == 1) {
            List<User> listMitMitgliedern = (List<User>) repository.findAll();
            for (User user :
                    listMitMitgliedern) {
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
                            .equals("Jugendlich") && !checkUserUnderEighteen(user)) {
                        throw new IllegalArgumentException("Der Benutzer ist bereits erwachsen und kann kein Jugendkonto einrichten.");
                    }
                    user.setActualAmount(evaluateAmountForUser(user));
                    createPaymentByUser(user);
                }
            }
        }

        List<User> list = (List<User>) repository.findAll();
        for (User user :
                list) {
            if (user.getMemberType().getDescription().equals("Jugendlich") && !checkUserUnderEighteen(user)) {
                Optional<MemberType> memberType = memberTypeService.findMemberTypeById("Vollmitglied");
                if (memberType.isPresent()) {
                    user.setMemberType(memberType.get());
                    updateUser(user.getUserId(), user);
                }
            }
        }

        return (List<User>) repository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return repository.findById(userId);
    }

    /**
     * Sets payments service.
     *
     * @param paymentsService the payments service
     */
    @Inject
    public void setPaymentsService(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    /**
     * Sets member type service.
     *
     * @param memberTypeService the member type service
     */
    @Inject
    public void setMemberTypeService(MemberTypeService memberTypeService) {
        this.memberTypeService = memberTypeService;
    }

    /**
     * Sets postcode service.
     *
     * @param postcodeService the postcode service
     */
    @Inject
    public void setPostcodeService(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    /**
     * Check user is family user.
     *
     * @param id        the id
     * @param savedUser the saved user
     */
    private void checkUserIsFamilyUser(long id, User savedUser) {
        if (savedUser.getFamilyId() != null && id == savedUser.getFamilyId().getUserId()) {
            throw new IllegalArgumentException("Der Benutzer kann nicht auf sich selber als Familienmitglied referenzieren.");
        }
    }

    /**
     * Update payments by user.
     *
     * @param id         the id
     * @param updateUser the update user
     */
    private void updatePaymentsByUser(long id, User updateUser) {
        Long invoiceNumber = paymentsService.findPaymentsByUserId(id, LocalDate.now().getYear());
        if (invoiceNumber != null) {
            Optional<Payments> payments = paymentsService.findPaymentsById(invoiceNumber);
            if (payments.isPresent()) {
                payments.get().setBankAccountDetails(updateUser.getBankAccountDetails());
                paymentsService.updatePayments(invoiceNumber, payments.get());
            }
        }
    }

    /**
     * Create payment by user.
     *
     * @param savedUser the saved user
     */
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

    /**
     * Evaluate amount for user double.
     *
     * @param createUser the create user
     * @return the double
     */
    private Double evaluateAmountForUser(User createUser) {
        Double amountResult = 0.0;
        Optional<MemberType> a = memberTypeService.findMemberTypeById(createUser
                .getMemberType()
                .getDescription());
        if (a.isPresent()) {
            if (createUser.getFamilyId() != null) {
                amountResult -= 3;
            }
            amountResult += a
                    .get()
                    .getAmount();

        }
        return amountResult;
    }

    /**
     * Check user under eighteen boolean.
     *
     * @param createUser the create user
     * @return the boolean
     */
    private boolean checkUserUnderEighteen(User createUser) {
        Period period = Period.between(createUser.getBirthday(), LocalDate.now());
        return period.getYears() < 18;
    }

    /**
     * Check boolean.
     *
     * @param createUser the create user
     * @return the boolean
     */
    private boolean check(User createUser) {
        Period period = Period.between(createUser.getBirthday(), LocalDate.now());
        return period.getYears() >= 18;
    }

    /**
     * Exists member type in db boolean.
     *
     * @param createUser the create user
     * @return the boolean
     */
    private boolean existsMemberTypeInDB(User createUser) {
        return this.memberTypeRepository.existsById(createUser
                .getMemberType()
                .getDescription());
    }

    /**
     * Exists postal code in db boolean.
     *
     * @param createUser the create user
     * @return the boolean
     */
    private boolean existsPostalCodeInDB(User createUser) {
        return this.postcodeRepository.existsById(createUser
                .getAddress()
                .getPostalCode()
                .getPostcode());
    }

    /**
     * Compute and insert leaving date.
     *
     * @param updateUser     the update user
     * @param persistentUser the persistent user
     */
    private void computeAndInsertLeavingDate(final User updateUser, final Optional<User> persistentUser) {
        if (updateUser.getCancellationDate() != null) {
            LocalDate regularLeavingDate = LocalDate.of(updateUser
                    .getCancellationDate()
                    .getYear(), 12, 31);
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

    /**
     * Sets leaving date.
     *
     * @param createUser the create user
     * @return the leaving date
     */
    private User setLeavingDate(final User createUser) {
        if (createUser.getCancellationDate() != null) {
            LocalDate regularLeavingDate = LocalDate.of(createUser
                    .getCancellationDate()
                    .getYear(), 12, 31);
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

    /**
     * Validate input user for update and insert.
     *
     * @param user the user
     */
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

        if (user
                .getMemberType()
                .getDescription()
                .equals("Jugendlich") && !checkUserUnderEighteen(user)) {
            throw new IllegalArgumentException("Der Benutzer ist bereits erwachsen und kann kein Jugendkonto einrichten.");
        }

        if ((user
                .getMemberType()
                .getDescription()
                .equals("Vollmitglied") || user
                .getMemberType()
                .getDescription()
                .equals("Ermäßigt")) && checkUserUnderEighteen(user)) {
            throw new IllegalArgumentException("Der Benutzer ist jünger als 18 und daher kann ein Fördermitglied und Jugendkonto erstellt werden.");
        }

        if (user.getMemberTypeChange() != null && user.getMemberTypeChange().getDescription().equals(user.getMemberType().getDescription())) {
            throw new IllegalArgumentException("Die Mitgliedsart kann nicht auf die gleiche Mitgliedsart gewechselt werden.");
        }

        if (user.getFamilyId() != null && !repository.existsById(user.getFamilyId().getUserId())) {
            throw new IllegalArgumentException("Der Familienangehörige existiert nicht.");
        }
    }

    /**
     * Is street only text boolean.
     *
     * @param user the user
     * @return the boolean
     */
    private boolean isStreetOnlyText(User user) {
        return user
                .getAddress()
                .getStreet()
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
    }

    /**
     * Is first name only text boolean.
     *
     * @param user the user
     * @return the boolean
     */
    private boolean isFirstNameOnlyText(User user) {
        return user
                .getName()
                .getFirstName()
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
    }

    /**
     * Is last name only text boolean.
     *
     * @param user the user
     * @return the boolean
     */
    private boolean isLastNameOnlyText(User user) {
        return user
                .getName()
                .getLastName()
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
    }

    /**
     * Is birthday before entry date and now boolean.
     *
     * @param user the user
     * @return the boolean
     */
    private boolean isBirthdayBeforeEntryDateAndNow(User user) {
        return user
                .getBirthday()
                .isBefore(user.getEntryDate()) && user
                .getBirthday()
                .isBefore(LocalDate.now());
    }

    /**
     * Is entry date before cancellation date boolean.
     *
     * @param user the user
     * @return the boolean
     */
    private boolean isEntryDateBeforeCancellationDate(User user) {
        if (user.getCancellationDate() == null) {
            return true;
        }
        return user
                .getEntryDate()
                .isBefore(user.getCancellationDate());
    }
}
