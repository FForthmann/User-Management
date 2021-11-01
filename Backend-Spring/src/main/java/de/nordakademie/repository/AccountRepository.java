package de.nordakademie.repository;

import de.nordakademie.model.Account;
import de.nordakademie.model.User;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
