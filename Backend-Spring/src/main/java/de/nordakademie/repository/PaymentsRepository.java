package de.nordakademie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import de.nordakademie.model.Payments;
@Repository
public interface PaymentsRepository extends CrudRepository<Payments, Long> {
    boolean existsUserInPayments(
            @Param("userId")
                    long userId);
    @Modifying
    @Query("UPDATE Payments SET userId = NUll WHERE userId.userId = ?1")
    void updateUserIdToNull(long userId);
}
