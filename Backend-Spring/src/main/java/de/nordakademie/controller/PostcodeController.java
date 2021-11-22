package de.nordakademie.controller;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.nordakademie.exceptions.CreateFailedException;
import de.nordakademie.exceptions.DeleteFailedException;
import de.nordakademie.exceptions.ReadFailedException;
import de.nordakademie.exceptions.UpdateFailedException;
import de.nordakademie.model.Postcode;
import de.nordakademie.service.PostcodeService;
import de.nordakademie.util.ExceptionMessages;
/**
 * The Spring Controller for Postcode
 *
 * @author Ridvan Cetin & Fabian Forthmann
 */
@RestController
@RequestMapping(path = "/rest/postcode")
public class PostcodeController {
    /**
     * The Service for processing postcode requests.
     */
    private PostcodeService service;

    /**
     * Sets service.
     *
     * @param service the service
     */
    @Inject
    public void setService(PostcodeService service) {
        this.service = service;
    }

    /**
     * Researches all postcodes and returns them as a list.
     *
     * @return list of all postcodes
     */
    @GetMapping
    public List<Postcode> findAllPostcodes() {
        return service.findAllPostcodes();
    }

    /**
     * Searches a user by its five-digit code and returns it.
     *
     * @param id the five-digit code
     * @return the postcode or an empty optional object
     * @throws ReadFailedException finding the postcode failed due to a specific failure
     */
    @GetMapping("/{id}")
    public Optional<Postcode> findPostcodeById(
            @PathVariable("id")
                    Long id) throws ReadFailedException {
        try {
            return service.findPostcodeById(id);
        } catch ( EntityNotFoundException ex ) {
            ex.printStackTrace();
            throw new ReadFailedException(ExceptionMessages.POSTCODE_READ_FAILED, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a postcode.
     *
     * @param postcode the postcode object
     * @return the created postcode as a response entity
     * @throws CreateFailedException creating the postcode failed due to a specific failure
     */
    @PostMapping
    public ResponseEntity<Postcode> createPostcode(
            @Valid
            @RequestBody
                    Postcode postcode) throws CreateFailedException {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createPostcode(postcode));
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new CreateFailedException(ExceptionMessages.POSTCODE_CREATION_FAILED + " " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a postcode by its five-digit code.
     *
     * @param id the five-digit code
     * @return an entity object with an HTTP status
     * @throws DeleteFailedException deleting the postcode failed due to a specific failure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Postcode> deletePostcode(
            @PathVariable("id")
                    Long id) throws DeleteFailedException {
        try {
            service.deletePostcodeById(id);
            return ResponseEntity
                    .ok()
                    .build();
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new DeleteFailedException(ExceptionMessages.POSTCODE_DELETE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch ( EmptyResultDataAccessException ex ) {
            throw new DeleteFailedException(ExceptionMessages.POSTCODE_NOT_FOUND_WHEN_DELETE, HttpStatus.BAD_REQUEST);
        } catch ( ConstraintViolationException ex ) {
            throw new DeleteFailedException(ExceptionMessages.POSTCODE_DELETE_REFERENCE_VIOLATED, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    /**
     * Updates a postcode.
     *
     * @param id the five-digit code
     * @param postcode the postcode object
     * @return the updated postcode as a response entity
     * @throws UpdateFailedException updating the postcode failed due to a specific failure
     */
    @PutMapping("/{id}")
    public ResponseEntity<Postcode> updatePostcode(
            @PathVariable("id")
                    Long id,
            @Valid
            @RequestBody
                    Postcode postcode) throws UpdateFailedException {
        try {
            service.updatePostcode(id, postcode);
            return ResponseEntity
                    .ok()
                    .build();
        } catch ( IllegalArgumentException ex ) {
            throw new UpdateFailedException(ExceptionMessages.POSTCODE_UPDATE_ILLEGAL_ARGUMENT + " " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch ( EntityNotFoundException ex ) {
            throw new UpdateFailedException(ExceptionMessages.POSTCODE_NOT_FOUND_WHEN_UPDATE, HttpStatus.NOT_FOUND);
        }
    }
}
