package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import de.nordakademie.model.MemberType;
import de.nordakademie.repository.MemberTypeRepository;
import de.nordakademie.util.ApiMessages;
/**
 * The member type service. Implements logic for processing member types.
 *
 * @author Ridvan Cetin, Fabian Forthmann
 */
@Service
@Transactional
public class MemberTypeServiceImpl implements MemberTypeService {
    /**
     * The repository to process member type database interactions.
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
