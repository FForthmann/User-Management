package de.nordakademie.repository;

import de.nordakademie.model.Account;
import de.nordakademie.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
