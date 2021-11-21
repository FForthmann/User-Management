package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import de.nordakademie.model.Payments;
public interface PaymentsService {
    Payments createPayments(Payments payments);

    void deletePaymentsById(Long accountId);

    void updatePayments(Long id, Payments payments);

    List<Payments> findAllPayments();

    Optional<Payments> findPaymentsById(Long accountId);

    boolean existsUserInPayments(long userId);

}
