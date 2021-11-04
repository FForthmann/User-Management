package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import de.nordakademie.model.Account;
import de.nordakademie.repository.AccountRepository;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AccountRepository repository;

    @Override
    public Account createAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public void deleteAccountById(Long accountId) {
        repository.deleteById(accountId);
    }

    @Override
    public void updateAccount(Long id, Account account) {
        Optional<Account> accountPersistent = repository.findById(id);
        accountPersistent
                .get()
                .setAmount(account.getAmount());
        accountPersistent
                .get()
                .setCountStatus(account.getCountStatus());
        accountPersistent
                .get()
                .setYear(account.getYear());
        accountPersistent
                .get()
                .setUserId(account.getUserId());
    }

    @Override
    public List<Account> findAllAccounts() {
        return (List<Account>) repository.findAll();
    }

    @Override
    public Optional<Account> findAccountById(Long accountId) {
        return repository.findById(accountId);
    }

    @Inject
    public void setRepository(AccountRepository repository) {
        this.repository = repository;
    }
}
