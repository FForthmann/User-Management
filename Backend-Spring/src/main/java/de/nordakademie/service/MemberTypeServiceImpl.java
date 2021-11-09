package de.nordakademie.service;

import de.nordakademie.model.MemberType;
import de.nordakademie.model.Postcode;
import de.nordakademie.repository.MemberTypeRepository;
import de.nordakademie.util.ApiMessages;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberTypeServiceImpl implements MemberTypeService{
    private MemberTypeRepository repository;

    @Inject
    public void setRepository(MemberTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public MemberType createMemberType(MemberType memberType) {

        // Check if JSON is filled correctly.
        if (checkMandatoryAttributesAreNotNull(memberType)){
            throw new IllegalArgumentException(ApiMessages.MSG_NULL);
        }

        return repository.save(memberType);
    }

    @Override
    public void deleteMemberTypeById(String memberTypeId) {
        repository.deleteById(memberTypeId);
    }

    @Override
    public void updateMemberType(String membertypeId, MemberType memberType) {

        // Check if JSON is filled correctly.
        if (checkMandatoryAttributesAreNotNull(memberType)){
            throw new IllegalArgumentException(ApiMessages.MSG_NULL);
        }

        Optional<MemberType> memberTypePersistent = repository.findById(membertypeId);
        if (!memberTypePersistent.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.MSG_ENTITY_NOT_EXISTS);
        }
        memberTypePersistent.get().setAmount(memberType.getAmount());
        memberTypePersistent.get().setDescription(memberType.getDescription());
    }

    @Override
    public List<MemberType> findAllMemberTypes() {
        return (List<MemberType>) repository.findAll();
    }

    @Override
    public Optional<MemberType> findMemberTypeById(String memberTypeId) {
        return repository.findById(memberTypeId);
    }


    private boolean checkMandatoryAttributesAreNotNull(MemberType createMemberType) {
        return isNull(createMemberType.getDescription(), createMemberType.getAmount());
    }

    private boolean isNull(Object... strArr) {
        for (Object st : strArr) {
            if (st == null)
                return true;
        }
        return false;
    }


}
