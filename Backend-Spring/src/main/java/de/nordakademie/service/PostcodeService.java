package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import de.nordakademie.model.Postcode;
/**
 * The interface for postcode service.
 *
 * @author Ridvan Cetin, Fabian Forthmann
 */
public interface PostcodeService {
    /**
     * Creates postcode.
     *
     * @param postcode the postcode
     * @return the created postcode
     */
    Postcode createPostcode(Postcode postcode);

    /**
     * Deletes postcode by its five-digit identifier.
     *
     * @param postcodeId the postcode fixe-digit identifier
     */
    void deletePostcodeById(Long postcodeId);

    /**
     * Update postcode.
     *
     * @param id       the postcode fixe-digit identifier
     * @param postcode the postcode
     */
    void updatePostcode(Long id, Postcode postcode);

    /**
     * Finds all postcodes.
     *
     * @return all postcodes as a list
     */
    List<Postcode> findAllPostcodes();

    /**
     * Finds postcode by id.
     *
     * @param postcode the five-digit identifier of postcode
     * @return the postcode
     */
    Optional<Postcode> findPostcodeById(Long postcode);

    boolean existsById(Long postcode);
}
