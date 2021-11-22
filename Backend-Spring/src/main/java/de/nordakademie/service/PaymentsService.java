package de.nordakademie.service;

import de.nordakademie.model.Payments;

import java.util.List;
import java.util.Optional;

/**
 * The interface Payments service.
 */
public interface PaymentsService {
    /**
     * Create payments payments.
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
     * Update payments.
     *
     * @param id       the id
     * @param payments the payments
     */
    void updatePayments(Long id, Payments payments);

    /**
     * Find all payments list.
     *
     * @return the list
     */
    List<Payments> findAllPayments();

    /**
     * Find payments by id optional.
     *
     * @param accountId the account id
     * @return the optional
     */
    Optional<Payments> findPaymentsById(Long accountId);

    /**
     * Exists user in payments boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    boolean existsUserInPayments(long userId);

    /**
     * Exists user in payments for this year boolean.
     *
     * @param userId the user id
     * @param year   the year
     * @return the boolean
     */
    boolean existsUserInPaymentsForThisYear(long userId, long year);

    /**
     * Find payments by user id long.
     *
     * @param userId the user id
     * @param year   the year
     * @return the long
     */
    Long findPaymentsByUserId(long userId, long year);

}
