package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import de.nordakademie.model.Account;
public interface AccountService {
    Account createAccount(Account account);

    void deleteAccountById(Long accountId);

    void updateAccount(Long id, Account account);

    List<Account> findAllAccounts();

    Optional<Account> findAccountById(Long accountId);

}
