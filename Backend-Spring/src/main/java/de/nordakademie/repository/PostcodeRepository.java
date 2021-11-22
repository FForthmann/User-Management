package de.nordakademie.repository;

import de.nordakademie.model.Postcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Postcode repository.
 */
@Repository
public interface PostcodeRepository extends CrudRepository<Postcode, Long> {
}
