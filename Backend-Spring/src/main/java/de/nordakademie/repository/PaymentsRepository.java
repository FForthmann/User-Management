package de.nordakademie.repository;

import de.nordakademie.model.Payments;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Payments repository.
 */
@Repository
public interface PaymentsRepository extends CrudRepository<Payments, Long> {
    /**
     * Exists user in payments boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    boolean existsUserInPayments(
            @Param("userId")
                    long userId);

    /**
     * Update user id to null.
     *
     * @param userId the user id
     */
    @Modifying
    @Query("UPDATE Payments SET userId = NUll WHERE userId.userId = ?1")
    void updateUserIdToNull(long userId);

    /**
     * Exists user in payments for this year boolean.
     *
     * @param userId the user id
     * @param year   the year
     * @return the boolean
     */
    boolean existsUserInPaymentsForThisYear(@Param("userId") long userId, @Param("year") long year);


    /**
     * Find payments by user id long.
     *
     * @param userId the user id
     * @param year   the year
     * @return the long
     */
    Long findPaymentsByUserId(@Param("userId") long userId, @Param("year") long year);
}
