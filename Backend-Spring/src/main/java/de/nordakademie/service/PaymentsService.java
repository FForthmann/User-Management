package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import de.nordakademie.model.Payments;
public interface PaymentsService {
    Payments createAccount(Payments payments);

    void deleteAccountById(Long accountId);

    void updateAccount(Long id, Payments payments);

    List<Payments> findAllAccounts();

    Optional<Payments> findAccountById(Long accountId);

}
