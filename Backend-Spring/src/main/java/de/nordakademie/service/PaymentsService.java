package de.nordakademie.service;

import de.nordakademie.model.Payments;

import java.util.List;
import java.util.Optional;

public interface PaymentsService {
    Payments createPayments(Payments payments);

    void deletePaymentsById(Long accountId);

    void updatePayments(Long id, Payments payments);

    List<Payments> findAllPayments();

    Optional<Payments> findPaymentsById(Long accountId);

    boolean existsUserInPayments(long userId);

    boolean existsUserInPaymentsForThisYear(long userId, long year);

    Long findPaymentsByUserId(long userId, long year);

}
