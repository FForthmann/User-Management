package de.nordakademie.service;

import de.nordakademie.model.Account;
import de.nordakademie.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
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
    public void updateAccount(Account account) {

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
