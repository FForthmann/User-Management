package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import de.nordakademie.exceptions.CreateFailedException;
import org.springframework.stereotype.Service;
import de.nordakademie.model.Postcode;
import de.nordakademie.repository.PostcodeRepository;
import de.nordakademie.util.ApiMessages;
@Service
@Transactional
public class PostcodeServiceImpl implements PostcodeService {
    private PostcodeRepository repository;

    private UserService userService;

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Inject
    public void setRepository(PostcodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Postcode createPostcode(Postcode postcode) {

        // Standard validation is checked by JPA
        // Technical requirements

        // Postcode has the size of 5
        if(checkPostcode(postcode)){
            throw new IllegalArgumentException("Msg");
        }

        // Location contains only Letters
        if (!checkLocation(postcode)){
            throw new IllegalArgumentException("Msg");
        }

        return repository.save(postcode);
    }

    private boolean checkLocation(Postcode postcode) {
            return postcode.getLocation().chars().allMatch(Character::isLetter);
    }

    private boolean checkPostcode(Postcode postcode) {
        Long postcodeNumber = postcode.getPostcode();
        return postcodeNumber.toString().length() != 5;
    }

    @Override
    public void deletePostcodeById(Long postcodeId) {
        repository.deleteById(postcodeId);
    }

    @Override
    public void updatePostcode(Long id, Postcode postcodeUpdate) {

        // Standard validation is checked by JPA
        // Technical requirements

        // Postcode has the size of 5
        if(checkPostcode(postcodeUpdate)){
            throw new IllegalArgumentException("Msg");
        }

        // Location contains only Letters
        if (checkLocation(postcodeUpdate)){
            throw new IllegalArgumentException("Msg");
        }

        Optional<Postcode> postcodePersistent = repository.findById(id);
        if (!postcodePersistent.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.MSG_ENTITY_NOT_EXISTS);
        }

        // Update DB
        postcodePersistent
                .get()
                .setPostcode(postcodeUpdate.getPostcode());
        postcodePersistent
                .get()
                .setLocation(postcodeUpdate.getLocation());
    }

    @Override
    public List<Postcode> findAllPostcodes() {
        return (List<Postcode>) repository.findAll();
    }

    @Override
    public Optional<Postcode> findPostcodeById(Long postcodeId) {
        return repository.findById(postcodeId);
    }



}
