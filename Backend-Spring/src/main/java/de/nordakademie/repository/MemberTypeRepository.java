package de.nordakademie.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import de.nordakademie.model.MemberType;
@Repository
public interface MemberTypeRepository extends CrudRepository<MemberType, String> {
}
