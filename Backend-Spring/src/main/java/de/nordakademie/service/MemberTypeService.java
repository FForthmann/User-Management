package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import de.nordakademie.model.MemberType;
/**
 * The interface for member type service.
 *
 * @author Ridvan Cetin, Fabian Forthmann
 */
public interface MemberTypeService {
    /**
     * Creates member type.
     *
     * @param memberType the member type
     * @return the member type
     */
    MemberType createMemberType(MemberType memberType);

    /**
     * Deletse member type by identifier, which is its name.
     *
     * @param memberTypeId the member type name
     */
    void deleteMemberTypeById(String memberTypeId);

    /**
     * Updates member type by identifier, which is its name.
     *
     * @param memberTypeId the member type name
     * @param memberType   the member type
     */
    void updateMemberType(String memberTypeId, MemberType memberType);

    /**
     * Find all member types.
     *
     * @return list of all member types
     */
    List<MemberType> findAllMemberTypes();

    /**
     * Find member type by identifier, which is its name.
     *
     * @param memberTypeId the member type name
     * @return the membertype
     */
    Optional<MemberType> findMemberTypeById(String memberTypeId);

    /**
     * Checks, if a given member type exists by identifier, which is its name.
     *
     * @param description the member type name
     * @return is the membertype exists
     */
    boolean existsById(String description);
}
