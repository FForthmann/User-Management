package de.nordakademie.service;

import de.nordakademie.model.MemberType;

import java.util.List;
import java.util.Optional;

public interface MemberTypeService {

    MemberType createPostcode(MemberType memberType);

    void deleteMemberTypeById(String memberTypeId);

    void updateMemberType(MemberType memberType);

    List<MemberType> findAllMemberTypes();

    Optional<MemberType> findMemberTypeById(String memberTypeId);

}
