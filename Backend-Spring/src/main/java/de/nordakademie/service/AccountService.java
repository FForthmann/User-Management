package de.nordakademie.service;



import de.nordakademie.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {


    Account createAccount(Account account);

    void deleteMAccountById(Long acoountId);

    void updateAccount(Account account);

    List<Account> findAllAccounts();

    Optional<Account> findAccountById(Long accountId);

}
