package de.nordakademie.repository;

import de.nordakademie.model.Payments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends CrudRepository<Payments, Long> {
}
