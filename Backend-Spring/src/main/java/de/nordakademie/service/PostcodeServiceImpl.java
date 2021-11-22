package de.nordakademie.service;

import de.nordakademie.model.Postcode;
import de.nordakademie.repository.PostcodeRepository;
import de.nordakademie.util.ApiMessages;
import de.nordakademie.util.ExceptionMessages;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * The type Postcode service.
 */
@Service
@Transactional
public class PostcodeServiceImpl implements PostcodeService {
    /**
     * The Repository.
     */
    private PostcodeRepository repository;

    /**
     * The User service.
     */
    private UserService userService;

    /**
     * Sets user service.
     *
     * @param userService the user service
     */
    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Sets repository.
     *
     * @param repository the repository
     */
    @Inject
    public void setRepository(PostcodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Postcode createPostcode(Postcode postcode) {

        validateInputPostcodeForUpdateAndInsert(postcode);

        return repository.save(postcode);
    }

    @Override
    public void deletePostcodeById(Long postcodeId) {
        repository.deleteById(postcodeId);
    }

    @Override
    public void updatePostcode(Long id, Postcode postcodeUpdate) {

        validateInputPostcodeForUpdateAndInsert(postcodeUpdate);

        Optional<Postcode> postcodePersistent = repository.findById(id);
        if (!postcodePersistent.isPresent()) {
            throw new EntityNotFoundException(ApiMessages.ENTITY_NOT_EXISTS);
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

    /**
     * Validate input postcode for update and insert.
     *
     * @param postcode the postcode
     */
    private void validateInputPostcodeForUpdateAndInsert(final Postcode postcode) {
        if (!hasPostalCodeFiveDigits(postcode)) {
            throw new IllegalArgumentException(ExceptionMessages.USER_POSTCODE_NOT_FIVE_DIGITS);
        }

        if (!isLocationOnlyText(postcode)) {
            throw new IllegalArgumentException(ExceptionMessages.USER_LOCATION_ONLY_TEXT);
        }
    }

    /**
     * Is location only text boolean.
     *
     * @param postcode the postcode
     * @return the boolean
     */
    private boolean isLocationOnlyText(Postcode postcode) {
        return postcode
                .getLocation()
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
    }

    /**
     * Has postal code five digits boolean.
     *
     * @param postcode the postcode
     * @return the boolean
     */
    private boolean hasPostalCodeFiveDigits(Postcode postcode) {
        Long postcodeNumber = postcode.getPostcode();
        return postcodeNumber
                .toString()
                .length() == 5;
    }

}
