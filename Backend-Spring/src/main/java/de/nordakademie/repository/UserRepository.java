package de.nordakademie.repository;

import de.nordakademie.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean isPostcodeInUse(@Param("postcode") long postcode);

}
