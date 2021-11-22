package de.nordakademie.service;

import de.nordakademie.model.Payments;
import de.nordakademie.repository.PaymentsRepository;
import de.nordakademie.util.ApiMessages;
import de.nordakademie.util.ExceptionMessages;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Payments Service Implementation
 */
@Service
@Transactional
public class PaymentsServiceImpl implements PaymentsService {
    /**
     * The Repository.
     */
    private PaymentsRepository repository;

    /**
     * The User service.
     */
    private UserService userService;

    /**
     * Set the User Service
     *
     * @param userService new {@Link UserService}
     */
    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Set the Payments Repository
     *
     * @param repository new {@Link PaymentsRepository}
     */
    @Inject
    public void setRepository(PaymentsRepository repository) {
        this.repository = repository;
    }

    /**
     * @param payments Payments object to be added
     * @return Payments Object
     */
    @Override
    public Payments createPayments(Payments payments) {

        if (!areBankAccountDetailsValid(payments)) {
            throw new IllegalArgumentException(ExceptionMessages.IBAN_NOT_VALID);
        }

        // Check if User exists in DB
        if (payments.getUserId() == null || !existsUserInDB(payments)) {
            throw new IllegalArgumentException(ApiMessages.USER_NOT_IN_DB);
        }

        return repository.save(payments);
    }

    /**
     * Delete Payments in DB
     *
     * @param paymentsId Id from Payments
     */
    @Override
    public void deletePaymentsById(Long paymentsId) {

        repository.deleteById(paymentsId);
    }

    /**
     * Update Payments
     *
     * @param id       ID from Payments
     * @param payments Payments Object to be updated
     */
    @Override
    public void updatePayments(Long id, Payments payments) {

        if (!areBankAccountDetailsValid(payments)) {
            throw new IllegalArgumentException(ExceptionMessages.IBAN_NOT_VALID);
        }

        Optional<Payments> accountPersistent = repository.findById(id);
        if (!accountPersistent.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.ENTITY_NOT_EXISTS);
        }

        // Update Payments in DB
        accountPersistent
                .get()
                .setAmount(payments.getAmount());
        accountPersistent
                .get()
                .setCountStatus(payments.getCountStatus());
        accountPersistent
                .get()
                .setYear(payments.getYear());
        accountPersistent
                .get()
                .setUserId(payments.getUserId());
        accountPersistent
                .get()
                .setBankAccountDetails(payments.getBankAccountDetails());
    }

    /**
     * Returns a list of all payments
     *
     * @return Gives a list with all payments
     */
    @Override
    public List<Payments> findAllPayments() {
        return (List<Payments>) repository.findAll();
    }

    /**
     * Returns Payment Object if present
     *
     * @param paymentsId Id of payment
     * @return Payments Object if present
     */
    @Override
    public Optional<Payments> findPaymentsById(Long paymentsId) {
        return repository.findById(paymentsId);
    }

    @Override
    public boolean existsUserInPayments(long userId) {
        return repository.existsUserInPayments(userId);
    }

    @Override
    public boolean existsUserInPaymentsForThisYear(long userId, long year) {

        return repository.existsUserInPaymentsForThisYear(userId, year);
    }

    @Override
    public Long findPaymentsByUserId(long userId, long year) {
        return repository.findPaymentsByUserId(userId, year);
    }

    /**
     * Are bank account details valid boolean.
     *
     * @param payments the payments
     * @return the boolean
     */
    private boolean areBankAccountDetailsValid(Payments payments) {

        String bankAccountDetails = payments.getBankAccountDetails();

        String[] array = bankAccountDetails.split("");
        if (array.length != 22) {
            return false;
        }
        if (!array[0]
                .chars()
                .allMatch(Character::isLetter) && !array[1]
                .chars()
                .allMatch(Character::isLetter)) {
            return false;
        } else {
            for (int i = 2; i < array.length; i++) {
                if (!array[i].matches("[0-9]")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if User exists in DB
     *
     * @param payments Payments Object
     * @return Boolean -Value if User exists in DB
     */
    private boolean existsUserInDB(Payments payments) {
        return this.userService
                .findUserById(payments
                        .getUserId()
                        .getUserId())
                .isPresent();
    }

}
