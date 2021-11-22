package de.nordakademie.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import de.nordakademie.model.Postcode;
/**
 * The interface for Postcode repository.
 */
@Repository
public interface PostcodeRepository extends CrudRepository<Postcode, Long> {
    @Override
    boolean existsById(Long aLong);
}
