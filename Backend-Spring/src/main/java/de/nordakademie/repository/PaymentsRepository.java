package de.nordakademie.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import de.nordakademie.model.Payments;
/**
 * The interface for Payments repository.
 */
@Repository
public interface PaymentsRepository extends CrudRepository<Payments, Long> {
    /**
     * Exists user in payments by user id.
     *
     * @param userId the user id
     * @return if the user exists in the payments table
     */
    boolean existsUserInPayments(
            @Param("userId")
                    long userId);

    /**
     * Updates user id to null in payments.
     *
     * @param userId the user id
     */
    @Modifying
    @Query("UPDATE Payments SET userId = NUll WHERE userId.userId = ?1")
    void updateUserIdToNull(long userId);

    /**
     * Exists user in payments for this year.
     *
     * @param userId the user id
     * @param year the year
     * @return if the user exists in the payments table for the given year
     */
    boolean existsUserInPaymentsForThisYear(
            @Param("userId")
                    long userId,
            @Param("year")
                    long year);

    /**
     * Finds payments by user id.
     *
     * @param userId the user id
     * @param year the year
     * @return invoice number of the searched payment in the given year
     */
    Long findPaymentsByUserId(
            @Param("userId")
                    long userId,
            @Param("year")
                    long year);
}
