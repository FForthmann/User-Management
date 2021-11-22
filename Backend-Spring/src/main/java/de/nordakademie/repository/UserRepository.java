package de.nordakademie.repository;

import de.nordakademie.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsFamilyIdByUserId(
            @Param("userId")
                    long userId);

    @Modifying
    @Query("UPDATE User SET familyId.userId = NUll WHERE familyId.userId = :userId")
    void updateFamilyIdToNullByUserId(@Param("userId") long userId);

}
