package de.nordakademie.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import de.nordakademie.model.Postcode;
@Repository
public interface PostcodeRepository extends CrudRepository<Postcode, Long> {
}
