package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import de.nordakademie.model.Payments;
import de.nordakademie.repository.PaymentsRepository;
import de.nordakademie.util.ApiMessages;
import de.nordakademie.util.ExceptionMessages;
/**
 * The payments service. Implements logic for processing payments.
 *
 * @author Ridvan Cetin, Fabian Forthmann
 */
@Service
@Transactional
public class PaymentsServiceImpl implements PaymentsService {
    /**
     * The repository to process payments interactions.
     */
    private PaymentsRepository repository;

    /**
     * The user service for processing user connected requests.
     */
    private UserService userService;

    /**
     * Set the user Service
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

    @Override
    public Payments createPayment(Payments payment) {

        if (!areBankAccountDetailsValid(payment)) {
            throw new IllegalArgumentException(ExceptionMessages.IBAN_NOT_VALID);
        }

        // Check if User exists in database
        if (payment.getUserId() == null || !existsUserInDB(payment)) {
            throw new IllegalArgumentException(ApiMessages.USER_NOT_IN_DB);
        }

        return repository.save(payment);
    }

    @Override
    public void deletePaymentById(Long paymentsId) {

        repository.deleteById(paymentsId);
    }

    @Override
    public void updatePayment(Long id, Payments payment) {

        if (!areBankAccountDetailsValid(payment)) {
            throw new IllegalArgumentException(ExceptionMessages.IBAN_NOT_VALID);
        }

        // Database entity
        Optional<Payments> accountPersistent = repository.findById(id);
        if (!accountPersistent.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.ENTITY_NOT_EXISTS);
        }

        // Update payments in database
        accountPersistent
                .get()
                .setAmount(payment.getAmount());
        accountPersistent
                .get()
                .setCountStatus(payment.getCountStatus());
        accountPersistent
                .get()
                .setYear(payment.getYear());
        accountPersistent
                .get()
                .setUserId(payment.getUserId());
        accountPersistent
                .get()
                .setBankAccountDetails(payment.getBankAccountDetails());
    }

    @Override
    public List<Payments> findAllPayments() {
        return (List<Payments>) repository.findAll();
    }

    @Override
    public Optional<Payments> findPaymentById(Long paymentsId) {
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
    public Long findPaymentByUserId(long userId, long year) {
        return repository.findPaymentsByUserId(userId, year);
    }

    @Override
    public void updateUserIdToNull(long userId) {
        repository.updateUserIdToNull(userId);
    }

    /**
     * Checks if bank account details are valid.
     *
     * @param payment payment which has to be validated
     * @return if the bank account details are valid
     */
    private boolean areBankAccountDetailsValid(Payments payment) {

        String bankAccountDetails = payment.getBankAccountDetails();

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
            for ( int i = 2; i < array.length; i++ ) {
                if (!array[i].matches("[0-9]")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if user exists in database
     *
     * @param payment payment which has to be validated
     * @return if the user exists in the payments database
     */
    private boolean existsUserInDB(Payments payment) {
        return this.userService
                .findUserById(payment
                                      .getUserId()
                                      .getUserId())
                .isPresent();
    }
}
