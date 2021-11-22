package de.nordakademie.service;

import de.nordakademie.model.MemberType;
import de.nordakademie.repository.MemberTypeRepository;
import de.nordakademie.util.ApiMessages;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * The type Member type service.
 */
@Service
@Transactional
public class MemberTypeServiceImpl implements MemberTypeService {
    /**
     * The Repository.
     */
    private MemberTypeRepository repository;

    /**
     * Sets repository.
     *
     * @param repository the repository
     */
    @Inject
    public void setRepository(MemberTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public MemberType createMemberType(MemberType memberType) {
        return repository.save(memberType);
    }

    @Override
    public void deleteMemberTypeById(String memberTypeId) {
        repository.deleteById(memberTypeId);
    }

    @Override
    public void updateMemberType(String membertypeId, MemberType memberType) {


        Optional<MemberType> memberTypePersistent = repository.findById(membertypeId);
        if (!memberTypePersistent.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.ENTITY_NOT_EXISTS);
        }
        memberTypePersistent
                .get()
                .setAmount(memberType.getAmount());
        memberTypePersistent
                .get()
                .setDescription(memberType.getDescription());
    }

    @Override
    public List<MemberType> findAllMemberTypes() {
        return (List<MemberType>) repository.findAll();
    }

    @Override
    public Optional<MemberType> findMemberTypeById(String memberTypeId) {
        return repository.findById(memberTypeId);
    }

    @Override
    public boolean existsById(String description) {
        return repository.existsById(description);
    }


}
