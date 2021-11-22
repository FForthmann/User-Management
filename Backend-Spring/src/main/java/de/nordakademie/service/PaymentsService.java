package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import de.nordakademie.model.Payments;
/**
 * The interface for payments service.
 *
 * @autor Ridvan Cetin, Fabian Forthmann
 */
public interface PaymentsService {
    /**
     * Creates a payment.
     *
     * @param payment the payment
     * @return the payment
     */
    Payments createPayment(Payments payment);

    /**
     * Delete payment by id.
     *
     * @param accountId the payment id which is the invoice number
     */
    void deletePaymentById(Long accountId);

    /**
     * Update payment by id.
     *
     * @param id the payment id which is the invoice number
     * @param payment the payment
     */
    void updatePayment(Long id, Payments payment);

    /**
     * Find all payments.
     *
     * @return list of all payments
     */
    List<Payments> findAllPayments();

    /**
     * Find payment by id.
     *
     * @param accountId the payment id which is the invoice number
     * @return the payment
     */
    Optional<Payments> findPaymentById(Long accountId);

    /**
     * Exists user in payments.
     *
     * @param userId the user id
     * @return is the user exists in the payments table
     */
    boolean existsUserInPayments(long userId);

    /**
     * Exists user in payments for this year.
     *
     * @param userId the user id
     * @param year   the year
     * @return is the user exists in the payments table for the given year
     */
    boolean existsUserInPaymentsForThisYear(long userId, long year);

    /**
     * Find payment by user id.
     *
     * @param userId the user id
     * @param year   the year
     * @return the identifier for the searched payment with the given user id and the given year
     */
    Long findPaymentByUserId(long userId, long year);

    void updateUserIdToNull(long userId);

}
