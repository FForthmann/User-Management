package de.nordakademie.service;

import de.nordakademie.model.MemberType;

import java.util.List;
import java.util.Optional;

/**
 * The interface Member type service.
 */
public interface MemberTypeService {
    /**
     * Create member type member type.
     *
     * @param memberType the member type
     * @return the member type
     */
    MemberType createMemberType(MemberType memberType);

    /**
     * Delete member type by id.
     *
     * @param memberTypeId the member type id
     */
    void deleteMemberTypeById(String memberTypeId);

    /**
     * Update member type.
     *
     * @param memberTypeId the member type id
     * @param memberType   the member type
     */
    void updateMemberType(String memberTypeId, MemberType memberType);

    /**
     * Find all member types list.
     *
     * @return the list
     */
    List<MemberType> findAllMemberTypes();

    /**
     * Find member type by id optional.
     *
     * @param memberTypeId the member type id
     * @return the optional
     */
    Optional<MemberType> findMemberTypeById(String memberTypeId);

}
