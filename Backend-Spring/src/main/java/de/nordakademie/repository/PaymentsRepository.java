package de.nordakademie.repository;

import de.nordakademie.model.Payments;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends CrudRepository<Payments, Long> {
    boolean existsUserInPayments(
            @Param("userId")
                    long userId);

    @Modifying
    @Query("UPDATE Payments SET userId = NUll WHERE userId.userId = ?1")
    void updateUserIdToNull(long userId);

    boolean existsUserInPaymentsForThisYear(@Param("userId") long userId, @Param("year") long year);


    Long findPaymentsByUserId(@Param("userId") long userId, @Param("year") long year);
}
