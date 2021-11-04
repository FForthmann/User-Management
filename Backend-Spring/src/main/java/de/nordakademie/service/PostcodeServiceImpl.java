package de.nordakademie.service;

import de.nordakademie.model.Postcode;
import de.nordakademie.model.User;
import de.nordakademie.repository.PostcodeRepository;
import de.nordakademie.util.ApiMessages;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostcodeServiceImpl implements PostcodeService {

    private PostcodeRepository repository;

    @Inject
    public void setRepository(PostcodeRepository repository) {

        this.repository = repository;
    }

    @Override
    public Postcode createPostcode(Postcode postcode) {

        // Validation
        if (checkMandatoryAttributesAreNotNull(postcode)){
            throw new IllegalArgumentException(ApiMessages.MSG_NULL);
        }

        return repository.save(postcode);
    }

    @Override
    public void deletePostcodeById(Long postcodeId) {



        repository.deleteById(postcodeId);
    }

    @Override
    public void updatePostcode(Long id, Postcode postcodeUpdate) {

        if (checkMandatoryAttributesAreNotNull(postcodeUpdate)){
            throw new IllegalArgumentException(ApiMessages.MSG_NULL);
        }


        Optional<Postcode> postcodePersistent = repository.findById(id);
        if (postcodePersistent.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.MSG_ENTITY_NOT_EXISTS);
        }

        postcodePersistent.get().setPostcode(postcodeUpdate.getPostcode());
        postcodePersistent.get().setLocation(postcodeUpdate.getLocation());
    }

    @Override
    public List<Postcode> findAllPostcodes() {

        return (List<Postcode>) repository.findAll();
    }

    @Override
    public Optional<Postcode> findPostcodeById(Long postcodeId) {

        return repository.findById(postcodeId);
    }

    private boolean checkMandatoryAttributesAreNotNull(Postcode createPostcode) {
        return isNull(createPostcode.getPostcode(), createPostcode.getLocation());
    }

    private boolean isNull(Object... strArr) {
        for (Object st : strArr) {
            if (st == null)
                return true;

        }
        return false;
    }


}
