package de.nordakademie.repository;

import de.nordakademie.model.Postcode;
import org.springframework.data.repository.CrudRepository;

public interface PostcodeRepository extends CrudRepository<Postcode, Long> {
}
