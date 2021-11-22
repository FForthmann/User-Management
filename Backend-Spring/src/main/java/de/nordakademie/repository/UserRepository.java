package de.nordakademie.repository;

import de.nordakademie.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Exists family id by user id.
     *
     * @param userId the user id
     * @return the boolean
     */
    boolean existsFamilyIdByUserId(
            @Param("userId")
                    long userId);

    /**
     * Update family id to null by user id.
     *
     * @param userId the user id
     */
    @Modifying
    @Query("UPDATE User SET familyId.userId = NUll WHERE familyId.userId = :userId")
    void updateFamilyIdToNullByUserId(@Param("userId") long userId);

}
