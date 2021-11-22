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
import de.nordakademie.repository.UserRepository;
import de.nordakademie.util.ApiMessages;
import de.nordakademie.util.ExceptionMessages;
import de.nordakademie.util.MemberTypes;
/**
 * The user service. Implements logic for processing user types.
 *
 * @author Ridvan Cetin, Fabian Forthmann
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {
    /**
     * The repository to process user database interactions.
     */
    private UserRepository repository;

    /**
     * The payments service for processing payment connected requests.
     */
    private PaymentsService paymentsService;

    /**
     * The member type service for processing member type connected requests.
     */
    private MemberTypeService memberTypeService;

    /**
     * The postcode service for processing postcode connected requests.
     */
    private PostcodeService postcodeService;

    /**
     * Sets user repository.
     *
     * @param repository the user repository
     */
    @Inject
    public void setRepository(UserRepository repository) {
        this.repository = repository;
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
        persistentUser
                .get()
                .setActualAmount(evaluateAmountForUser(updateUser));

        this.postcodeService.updatePostcode(updateUser
                                                    .getAddress()
                                                    .getPostalCode()
                                                    .getPostcode(), updateUser
                                                    .getAddress()
                                                    .getPostalCode());

        updatePaymentsByUser(id, updateUser);
    }

    @Override
    public void deleteUserById(long userId) {

        Optional<User> user = repository.findById(userId);
        if (!user.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.ENTITY_NOT_EXISTS);
        }

        if (paymentsService.existsUserInPayments(userId)) {
            paymentsService.updateUserIdToNull(userId);
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
            list
                    .stream()
                    .filter(user -> user.getLeavingDate() != null && user
                            .getLeavingDate()
                            .isEqual(localDate))
                    .forEach(user -> deleteUserById(user.getUserId()));
        }
        if (localDate
                .getMonth()
                .getValue() == 1 && localDate.getDayOfMonth() == 1) {
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
                            .equals("Jugendlich") && !checkUserUnderEighteen(user)) {
                        throw new IllegalArgumentException("Der Benutzer ist bereits erwachsen und kann kein Jugendkonto einrichten.");
                    }
                    user.setActualAmount(evaluateAmountForUser(user));
                    createPaymentByUser(user);
                }
            }
        }

        List<User> list = (List<User>) repository.findAll();
        for ( User user :
                list ) {
            if (user
                    .getMemberType()
                    .getDescription()
                    .equals("Jugendlich") && !checkUserUnderEighteen(user)) {
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
     * Checks if the user references himself as a familymember
     *
     * @param id the user id
     * @param savedUser the saved user whos family id will be checked
     */
    private void checkUserIsFamilyUser(long id, User savedUser) {
        if (savedUser.getFamilyId() != null && id == savedUser
                .getFamilyId()
                .getUserId()) {
            throw new IllegalArgumentException(ExceptionMessages.CYCLIC_FAMILY_ID_REFERENCE);
        }
    }

    /**
     * Updates bank account details in payments when they are edited in the user.
     *
     * @param id the user id for which payments are searched
     * @param updateUser the updated user from whom the bank account details will be copied
     */
    private void updatePaymentsByUser(long id, User updateUser) {
        Long invoiceNumber = paymentsService.findPaymentByUserId(id, LocalDate
                .now()
                .getYear());
        if (invoiceNumber != null) {
            Optional<Payments> payments = paymentsService.findPaymentById(invoiceNumber);
            if (payments.isPresent()) {
                payments
                        .get()
                        .setBankAccountDetails(updateUser.getBankAccountDetails());
                paymentsService.updatePayment(invoiceNumber, payments.get());
            }
        }
    }

    /**
     * Creates payment by user.
     *
     * @param savedUser the user on whom the payment will be created
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
        paymentsService.createPayment(payments);
    }

    /**
     * Calculates the individual amount for a user in respect of their member type and family id.
     *
     * @param createUser the user
     * @return the calculated amount
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
     * Checks if user is under eighteen.
     *
     * @param createUser the user
     * @return is the user is unter eighteen
     */
    private boolean checkUserUnderEighteen(User createUser) {
        Period period = Period.between(createUser.getBirthday(), LocalDate.now());
        return period.getYears() < 18;
    }

    /**
     * Checks if the membertype exists in the database.
     *
     * @param createUser the user
     * @return if the membertype of the user exists in the database
     */
    private boolean existsMemberTypeInDB(User createUser) {
        return this.memberTypeService.existsById(createUser
                                                         .getMemberType()
                                                         .getDescription());
    }

    /**
     * Checks if the postal code exists in the database.
     *
     * @param createUser the user
     * @return if the postal code of the user exists in the database
     */
    private boolean existsPostalCodeInDB(User createUser) {
        return this.postcodeService.existsById(createUser
                                                       .getAddress()
                                                       .getPostalCode()
                                                       .getPostcode());
    }

    /**
     * Computes and inserts leaving date.
     *
     * @param updateUser     the input user
     * @param persistentUser the already safed user in the database
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
     * @param createUser the user
     * @return the user with the field 'leavingDate' calculated and inserted
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
     * Validates input fileld from user for update and insert.
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
                .equals(MemberTypes.JUGENDLICH) && !checkUserUnderEighteen(user)) {
            throw new IllegalArgumentException(ExceptionMessages.YOUTH_ACCOUNT_MEMBER_IS_ADULT);
        }

        if ((user
                .getMemberType()
                .getDescription()
                .equals(MemberTypes.VOLLMITGLIED) || user
                .getMemberType()
                .getDescription()
                .equals(MemberTypes.ERÄßIGT)) && checkUserUnderEighteen(user)) {
            throw new IllegalArgumentException(ExceptionMessages.YOUTH_VALID_MEMBER_TYPES);
        }

        if (user.getMemberTypeChange() != null && user
                .getMemberTypeChange()
                .getDescription()
                .equals(user
                                .getMemberType()
                                .getDescription())) {
            throw new IllegalArgumentException(ExceptionMessages.CHANGE_SAME_MEMBERTYPE);
        }

        if (user.getFamilyId() != null && !repository.existsById(user
                                                                         .getFamilyId()
                                                                         .getUserId())) {
            throw new IllegalArgumentException(ExceptionMessages.FAMILY_MEMBER_DOES_NOT_EXIST);
        }
    }

    /**
     * Validates if the street only contains of text.
     *
     * @param user the user
     * @return the street only contains of text
     */
    private boolean isStreetOnlyText(User user) {
        return user
                .getAddress()
                .getStreet()
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
    }

    /**
     * Validates if the first name only contains of text.
     *
     * @param user the user
     * @return the first name only contains of text
     */
    private boolean isFirstNameOnlyText(User user) {
        return user
                .getName()
                .getFirstName()
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
    }

    /**
     * Validates if the last name only contains of text.
     *
     * @param user the user
     * @return the last name only contains of text
     */
    private boolean isLastNameOnlyText(User user) {
        return user
                .getName()
                .getLastName()
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
    }

    /**
     * Validates if the birthday is before entry date and today.
     *
     * @param user the user
     * @return if the birthday is before entry date and today
     */
    private boolean isBirthdayBeforeEntryDateAndNow(User user) {
        return user
                .getBirthday()
                .isBefore(user.getEntryDate()) && user
                .getBirthday()
                .isBefore(LocalDate.now());
    }

    /**
     * Validates if the entry date is before cancellation date.
     *
     * @param user the user
     * @return if the entry date is before cancellation date
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
