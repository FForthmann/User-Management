package de.nordakademie.service;

import de.nordakademie.model.MemberType;
import de.nordakademie.model.Postcode;
import de.nordakademie.repository.MemberTypeRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberTypeServiceImpl implements MemberTypeService{
    private MemberTypeRepository repository;

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

    @Inject
    public void setRepository(MemberTypeRepository repository) {
        this.repository = repository;
    }
}
