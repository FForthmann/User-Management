package de.nordakademie.service;

import de.nordakademie.model.Postcode;

import java.util.List;
import java.util.Optional;

/**
 * The interface Postcode service.
 */
public interface PostcodeService {
    /**
     * Create postcode.
     *
     * @param postcode the postcode
     * @return the postcode
     */
    Postcode createPostcode(Postcode postcode);

    /**
     * Delete postcode by id.
     *
     * @param postcodeId the postcode id
     */
    void deletePostcodeById(Long postcodeId);

    /**
     * Update postcode.
     *
     * @param id       the id
     * @param postcode the postcode
     */
    void updatePostcode(Long id, Postcode postcode);

    /**
     * Find all postcodes list.
     *
     * @return the list
     */
    List<Postcode> findAllPostcodes();

    /**
     * Find postcode by id optional.
     *
     * @param userId the user id
     * @return the optional
     */
    Optional<Postcode> findPostcodeById(Long postcode);


    boolean existsById(Long postcode);

}
