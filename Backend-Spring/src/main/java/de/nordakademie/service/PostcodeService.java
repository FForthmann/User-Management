package de.nordakademie.service;

import de.nordakademie.model.Postcode;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostcodeService {

    Postcode createPostcode(Postcode postcode);

    void deletePostcodeById(Long postcodeId);

    void updatePostcode(Long id, Postcode postcode);

    List<Postcode> findAllPostcodes();

    Optional<Postcode> findPostcodeById(Long userId);



}
