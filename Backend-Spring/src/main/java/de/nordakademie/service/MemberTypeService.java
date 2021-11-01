package de.nordakademie.service;

import de.nordakademie.model.MemberType;

import java.util.List;
import java.util.Optional;

public interface MemberTypeService {

    MemberType createMemberType(MemberType memberType);

    void deleteMemberTypeById(String memberTypeId);

    void updateMemberType(String memberTypeId, MemberType memberType);

    List<MemberType> findAllMemberTypes();

    Optional<MemberType> findMemberTypeById(String memberTypeId);

}
