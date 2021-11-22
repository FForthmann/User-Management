package de.nordakademie.repository;

import de.nordakademie.model.MemberType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Member type repository.
 */
@Repository
public interface MemberTypeRepository extends CrudRepository<MemberType, String> {
}
