package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import de.nordakademie.model.Postcode;
import de.nordakademie.repository.PostcodeRepository;
import de.nordakademie.util.ApiMessages;
import de.nordakademie.util.ExceptionMessages;
/**
 * The postcode service. Implements logic for processing postcodes.
 *
 * @author Ridvan Cetin, Fabian Forthmann
 */
@Service
@Transactional
public class PostcodeServiceImpl implements PostcodeService {
    /**
     * The repository to process postcode database interactions.
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

    @Override
    public boolean existsById(Long postcode) {
        return repository.existsById(postcode);
    }

    /**
     * Validate input postcode for update and insert.
     *
     * @param postcode the postcode to validate
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
     * Checks if the location only contains text.
     *
     * @param postcode the postcode on which the location has to be checked
     * @return if the location only contains text
     */
    private boolean isLocationOnlyText(Postcode postcode) {
        return postcode
                .getLocation()
                .matches("^([ \\u00c0-\\u01ffa-zA-Z'\\\\-])+$");
    }

    /**
     * Checks if the postcode consists of five digits.
     *
     * @param postcode the postcode to be checked
     * @return if the postcode consists of five digits
     */
    private boolean hasPostalCodeFiveDigits(Postcode postcode) {
        Long postcodeNumber = postcode.getPostcode();
        return postcodeNumber
                .toString()
                .length() == 5;
    }
}
