package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import de.nordakademie.model.Payments;
import de.nordakademie.repository.PaymentsRepository;
@Service
@Transactional
public class PaymentsServiceImpl implements PaymentsService {
    private PaymentsRepository repository;

    @Override
    public Payments createAccount(Payments payments) {
        return repository.save(payments);
    }

    @Override
    public void deleteAccountById(Long accountId) {
        repository.deleteById(accountId);
    }

    @Override
    public void updateAccount(Long id, Payments payments) {
        Optional<Payments> accountPersistent = repository.findById(id);
        accountPersistent
                .get()
                .setAmount(payments.getAmount());
        accountPersistent
                .get()
                .setCountStatus(payments.getCountStatus());
        accountPersistent
                .get()
                .setYear(payments.getYear());
        accountPersistent
                .get()
                .setUserId(payments.getUserId());
    }

    @Override
    public List<Payments> findAllAccounts() {
        return (List<Payments>) repository.findAll();
    }

    @Override
    public Optional<Payments> findAccountById(Long accountId) {
        return repository.findById(accountId);
    }

    @Inject
    public void setRepository(PaymentsRepository repository) {
        this.repository = repository;
    }
}
