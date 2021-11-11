package de.nordakademie.service;

import java.util.List;
import java.util.Optional;
import de.nordakademie.model.Postcode;
public interface PostcodeService {
    Postcode createPostcode(Postcode postcode);

    void deletePostcodeById(Long postcodeId);

    void updatePostcode(Long id, Postcode postcode);

    List<Postcode> findAllPostcodes();

    Optional<Postcode> findPostcodeById(Long userId);


}
