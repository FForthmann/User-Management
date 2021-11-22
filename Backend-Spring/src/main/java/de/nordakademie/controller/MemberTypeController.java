package de.nordakademie.controller;

import de.nordakademie.exceptions.CreateFailedException;
import de.nordakademie.exceptions.DeleteFailedException;
import de.nordakademie.exceptions.ReadFailedException;
import de.nordakademie.exceptions.UpdateFailedException;
import de.nordakademie.model.MemberType;
import de.nordakademie.service.MemberTypeService;
import de.nordakademie.util.ExceptionMessages;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rest/memberType")
public class MemberTypeController {
    private MemberTypeService service;

    @Inject
    public void setService(MemberTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<MemberType> findAllMemberType() {
        return service.findAllMemberTypes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MemberType> deleteMemberType(@PathVariable("id") String memberTypeId) throws DeleteFailedException {
        try {
            service.deleteMemberTypeById(memberTypeId);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            throw new DeleteFailedException(ExceptionMessages.MEMBERTYPE_DELETE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch (EmptyResultDataAccessException ex) {
            // ToDo fafor: Beim Delete eines nicht vorhandenen Membertypes wird der gewollte Text nicht angezeigt. Der Fehlercode ist korrekt.
            throw new DeleteFailedException(ExceptionMessages.MEMBERTYPE_NOT_FOUND_WHEN_DELETE, HttpStatus.BAD_REQUEST);
        } catch (ConstraintViolationException ex) {
            // ToDo fafor: Falsche Exception, wird nicht getriggert und endet am Frontend mit 500 Internal Error
            throw new DeleteFailedException(ExceptionMessages.MEMBERTYPE_DELETE_REFERENCE_VIOLATED, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/{id}")
    public Optional<MemberType> findMemberTypeById(
            @PathVariable("id")
                    String id) throws ReadFailedException {
        try {
            return service.findMemberTypeById(id);
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            throw new ReadFailedException(ExceptionMessages.MEMBERTYPE_READ_FAILED, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MemberType> createMemberType(
            @Valid @RequestBody
                    MemberType memberType) throws CreateFailedException {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createMemberType(memberType));
        } catch (IllegalArgumentException ex) {
            throw new CreateFailedException(ExceptionMessages.MEMBERTYPE_CREATION_FAILED, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberType> updateMemberType(
            @PathVariable("id")
                    String id,
            @Valid @RequestBody
                    MemberType memberType) throws UpdateFailedException {
        try {
            service.updateMemberType(id, memberType);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (IllegalArgumentException ex) {
            throw new UpdateFailedException(ExceptionMessages.MEMBERTYPE_UPDATE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException ex) {
            throw new UpdateFailedException(ExceptionMessages.MEMBERTYPE_NOT_FOUND_WHEN_UPDATE, HttpStatus.NOT_FOUND);
        }
    }

}
