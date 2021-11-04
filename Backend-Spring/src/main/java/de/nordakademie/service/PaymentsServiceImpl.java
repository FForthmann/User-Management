package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;

import de.nordakademie.model.Postcode;
import de.nordakademie.util.ApiMessages;
import org.springframework.stereotype.Service;
import de.nordakademie.model.Payments;
import de.nordakademie.repository.PaymentsRepository;
@Service
@Transactional
public class PaymentsServiceImpl implements PaymentsService {
    private PaymentsRepository repository;
    private UserService userService;

    @Inject
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Inject
    public void setRepository(PaymentsRepository repository) {

        this.repository = repository;
    }

    @Override
    public Payments createAccount(Payments payments) {

        if (checkMandatoryAttributesAreNotNull(payments)){
            throw new IllegalArgumentException(ApiMessages.MSG_NULL);
        }

        if (existsUserInDB(payments)) {
            throw new IllegalArgumentException(ApiMessages.MSG_USER_NOT_IN_DB);
        }

        return repository.save(payments);
    }

    private boolean existsUserInDB(Payments payments) {
        return this.userService.findUserById(payments.getUserId().getUserId()).isPresent();
    }

    @Override
    public void deleteAccountById(Long accountId) {



        repository.deleteById(accountId);
    }

    @Override
    public void updateAccount(Long id, Payments payments) {
        Optional<Payments> accountPersistent = repository.findById(id);
        accountPersistent.get().setAmount(payments.getAmount());
        accountPersistent.get().setCountStatus(payments.getCountStatus());
        accountPersistent.get().setYear(payments.getYear());
        accountPersistent.get().setUserId(payments.getUserId());
    }

    @Override
    public List<Payments> findAllAccounts() {

        return (List<Payments>) repository.findAll();
    }

    @Override
    public Optional<Payments> findAccountById(Long accountId) {

        return repository.findById(accountId);
    }


    private boolean checkMandatoryAttributesAreNotNull(Payments createPayments) {
        return isNull(createPayments.getAmount(), createPayments.getCountStatus(), createPayments.getYear(), createPayments.getUserId());
    }

    private boolean isNull(Object... strArr) {
        for (Object st : strArr) {
            if (st == null)
                return true;

        }
        return false;
    }

}
