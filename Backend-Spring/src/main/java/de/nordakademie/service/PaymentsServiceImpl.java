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
/**
 * Payments Service Implementation
 */
@Service
@Transactional
public class PaymentsServiceImpl implements PaymentsService {
    private PaymentsRepository repository;

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

        // Check if JSON is filled correctly.
       // if (checkMandatoryAttributesAreNotNull(payments)) {
        //    throw new IllegalArgumentException(ApiMessages.MSG_NULL);
       // }

        // Check if User exists in DB
        //if (!existsUserInDB(payments)) {
       //     throw new IllegalArgumentException(ApiMessages.MSG_USER_NOT_IN_DB);
     //   }

        return repository.save(payments);
    }

    /**
     * Check if User exists in DB
     *
     * @param payments Payments Object
     * @return Boolean-Value if User exists in DB
     */
    private boolean existsUserInDB(Payments payments) {
        return this.userService
                .findUserById(payments
                                      .getUserId()
                                      .getUserId())
                .isPresent();
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
     * @param id ID from Payments
     * @param payments Payments Object to be updated
     */
    @Override
    public void updatePayments(Long id, Payments payments) {

        // Check if JSON is filled correctly.
        if (checkMandatoryAttributesAreNotNull(payments)) {
            throw new IllegalArgumentException(ApiMessages.MSG_NULL);
        }

        // Check if User exists in DB
        if (!existsUserInDB(payments)) {
            throw new IllegalArgumentException(ApiMessages.MSG_USER_NOT_IN_DB);
        }

        Optional<Payments> accountPersistent = repository.findById(id);
        if (!accountPersistent.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.MSG_ENTITY_NOT_EXISTS);
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

    /**
     * Checks all mandatory attributes are not null
     *
     * @param createPayments Payments Object
     * @return Boolean-Value if the mandatory attributes are not null
     */
    private boolean checkMandatoryAttributesAreNotNull(Payments createPayments) {
        return isNull(createPayments.getAmount(), createPayments.getCountStatus(), createPayments.getYear(), createPayments.getUserId(),
                      createPayments.getBankAccountDetails());
    }

    /**
     * Checks if Strings are null or not null
     *
     * @param strArr Values of Strings
     * @return Boolean-Value if Strings are null or not
     */
    private boolean isNull(Object... strArr) {
        for ( Object st : strArr ) {
            if (st == null)
                return true;
        }
        return false;
    }

}
