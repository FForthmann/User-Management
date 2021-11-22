package de.nordakademie.repository;

import de.nordakademie.model.Postcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostcodeRepository extends CrudRepository<Postcode, Long> {
}
