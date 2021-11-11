package de.nordakademie.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import de.nordakademie.model.Payments;
@Repository
public interface PaymentsRepository extends CrudRepository<Payments, Long> {
    boolean existsUserInPayments(
            @Param("userId")
                    long userId);

    void deleteAllPaymentsByUserId(
            @Param("userId")
                    long userId);

}
