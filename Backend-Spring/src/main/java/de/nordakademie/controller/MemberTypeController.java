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
import de.nordakademie.model.MemberType;
import de.nordakademie.service.MemberTypeService;
import de.nordakademie.util.ExceptionMessages;
/**
 * The Spring Controller for Member type.
 *
 * @author Ridvan Cetin & Fabian Forthmann
 */
@RestController
@RequestMapping(path = "/rest/memberType")
public class MemberTypeController {
    /**
     * The Service for processing member type requests.
     */
    private MemberTypeService service;

    /**
     * Sets service.
     *
     * @param service the service
     */
    @Inject
    public void setService(MemberTypeService service) {
        this.service = service;
    }

    /**
     * Researches all member types and returns them as a list.
     *
     * @return list of all member types
     */
    @GetMapping
    public List<MemberType> findAllMemberType() {
        return service.findAllMemberTypes();
    }

    /**
     * Searches a member type by its name and returns it.
     *
     * @param id name of the member type
     * @return the member type or an empty optional object
     * @throws ReadFailedException finding the member type failed due to a specific failure
     */
    @GetMapping("/{id}")
    public Optional<MemberType> findMemberTypeById(
            @PathVariable("id")
                    String id) throws ReadFailedException {
        try {
            return service.findMemberTypeById(id);
        } catch ( EntityNotFoundException ex ) {
            ex.printStackTrace();
            throw new ReadFailedException(ExceptionMessages.MEMBERTYPE_READ_FAILED, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a member type.
     *
     * @param memberType the member type object
     * @return the created member type as a response entity
     * @throws CreateFailedException creating the member type failed due to a specific failure
     */
    @PostMapping
    public ResponseEntity<MemberType> createMemberType(
            @Valid
            @RequestBody
                    MemberType memberType) throws CreateFailedException {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createMemberType(memberType));
        } catch ( IllegalArgumentException ex ) {
            throw new CreateFailedException(ExceptionMessages.MEMBERTYPE_CREATION_FAILED, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a member type by its name.
     *
     * @param memberTypeId the name of the member type
     * @return an entity object with an HTTP status
     * @throws DeleteFailedException deleting the member type failed due to a specific failure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MemberType> deleteMemberType(
            @PathVariable("id")
                    String memberTypeId) throws DeleteFailedException {
        try {
            service.deleteMemberTypeById(memberTypeId);
            return ResponseEntity
                    .ok()
                    .build();
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new DeleteFailedException(ExceptionMessages.MEMBERTYPE_DELETE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch ( EmptyResultDataAccessException ex ) {
            throw new DeleteFailedException(ExceptionMessages.MEMBERTYPE_NOT_FOUND_WHEN_DELETE, HttpStatus.BAD_REQUEST);
        } catch ( ConstraintViolationException ex ) {
            throw new DeleteFailedException(ExceptionMessages.MEMBERTYPE_DELETE_REFERENCE_VIOLATED, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    /**
     * Updates a member type.
     *
     * @param id the name of the member type
     * @param memberType the member type object
     * @return the updated member type as a response entity
     * @throws UpdateFailedException updating the member type failed due to a specific failure
     */
    @PutMapping("/{id}")
    public ResponseEntity<MemberType> updateMemberType(
            @PathVariable("id")
                    String id,
            @Valid
            @RequestBody
                    MemberType memberType) throws UpdateFailedException {
        try {
            service.updateMemberType(id, memberType);
            return ResponseEntity
                    .ok()
                    .build();
        } catch ( IllegalArgumentException ex ) {
            throw new UpdateFailedException(ExceptionMessages.MEMBERTYPE_UPDATE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch ( EntityNotFoundException ex ) {
            throw new UpdateFailedException(ExceptionMessages.MEMBERTYPE_NOT_FOUND_WHEN_UPDATE, HttpStatus.NOT_FOUND);
        }
    }
}
