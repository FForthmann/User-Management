package de.nordakademie.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import de.nordakademie.model.MemberType;
/**
 *  The interface for Member type repository.
 */
@Repository
public interface MemberTypeRepository extends CrudRepository<MemberType, String> {
    @Override
    boolean existsById(String s);
}
