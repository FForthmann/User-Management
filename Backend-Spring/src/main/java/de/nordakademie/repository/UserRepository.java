package de.nordakademie.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import de.nordakademie.model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
