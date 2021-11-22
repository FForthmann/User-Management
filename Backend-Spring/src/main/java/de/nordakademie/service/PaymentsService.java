package de.nordakademie.service;

import de.nordakademie.model.Payments;

import java.util.List;
import java.util.Optional;

/**
 * The interface Payments service.
 */
public interface PaymentsService {
    /**
     * Create payments.
     *
     * @param payments the payments
     * @return the payments
     */
    Payments createPayments(Payments payments);

    /**
     * Delete payments by id.
     *
     * @param accountId the account id
     */
    void deletePaymentsById(Long accountId);

    /**
     * Update payments by id.
     *
     * @param id       the id
     * @param payments the payments
     */
    void updatePayments(Long id, Payments payments);

    /**
     * Find all payments.
     *
     * @return the list
     */
    List<Payments> findAllPayments();

    /**
     * Find payments by id.
     *
     * @param accountId the account id
     * @return the optional
     */
    Optional<Payments> findPaymentsById(Long accountId);

    /**
     * Exists user in payments.
     *
     * @param userId the user id
     * @return the boolean
     */
    boolean existsUserInPayments(long userId);

    /**
     * Exists user in payments for this year.
     *
     * @param userId the user id
     * @param year   the year
     * @return the boolean
     */
    boolean existsUserInPaymentsForThisYear(long userId, long year);

    /**
     * Find payments by user id.
     *
     * @param userId the user id
     * @param year   the year
     * @return the long
     */
    Long findPaymentsByUserId(long userId, long year);


    void updateUserIdToNull(long userId);

}
