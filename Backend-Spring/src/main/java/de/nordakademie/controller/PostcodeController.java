package de.nordakademie.controller;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
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
@RestController
@RequestMapping(path = "/rest/postcode")
public class PostcodeController {
    private PostcodeService service;

    @Inject
    public void setService(PostcodeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Postcode> findAllPostcodes() {
        return service.findAllPostcodes();
    }

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
            throw new DeleteFailedException(ExceptionMessages.POSTCODE_DELETE_ILLEGAL_ARGUMENT, ex, HttpStatus.BAD_REQUEST);
        } catch ( EmptyResultDataAccessException ex ) {
            // ToDo fafor: Beim Delete eines nicht vorhandenen Postcodes wird der gewollte Text nicht angezeigt. Der Fehlercode ist korrekt.
            throw new DeleteFailedException(ExceptionMessages.POSTCODE_NOT_FOUND_WHEN_DELETE, ex, HttpStatus.BAD_REQUEST);
        } catch ( ConstraintViolationException ex ) {
            // ToDo fafor: Falsche Exception, wird nicht getriggert und endet am Frontend mit 500 Internal Error
            throw new DeleteFailedException(ExceptionMessages.POSTCODE_DELETE_REFERENCE_VIOLATED, ex, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping
    public ResponseEntity<Postcode> createPostcode(
            @RequestBody
                    Postcode postcode) throws CreateFailedException {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createPostcode(postcode));
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new CreateFailedException(ExceptionMessages.POSTCODE_CREATION_FAILED, ex, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public Optional<Postcode> findPostcodeById(
            @PathVariable("id")
                    Long id) throws ReadFailedException {
        try {
            return service.findPostcodeById(id);
        } catch ( EntityNotFoundException ex ) {
            ex.printStackTrace();
            throw new ReadFailedException(ExceptionMessages.POSTCODE_READ_FAILED, ex, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postcode> updatePostcode(
            @PathVariable("id")
                    Long id,
            @RequestBody
                    Postcode postcode) throws UpdateFailedException {
        try {
            service.updatePostcode(id, postcode);
            return ResponseEntity
                    .ok()
                    .build();
        } catch ( IllegalArgumentException ex ) {
            throw new UpdateFailedException(ExceptionMessages.POSTCODE_UPDATE_ILLEGAL_ARGUMENT, ex, HttpStatus.BAD_REQUEST);
        } catch ( EntityNotFoundException ex ) {
            throw new UpdateFailedException(ExceptionMessages.POSTCODE_NOT_FOUND_WHEN_UPDATE, ex, HttpStatus.NOT_FOUND);
        }
    }
}
