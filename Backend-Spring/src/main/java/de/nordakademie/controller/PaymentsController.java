package de.nordakademie.controller;

import de.nordakademie.exceptions.CreateFailedException;
import de.nordakademie.exceptions.DeleteFailedException;
import de.nordakademie.exceptions.ReadFailedException;
import de.nordakademie.exceptions.UpdateFailedException;
import de.nordakademie.model.Payments;
import de.nordakademie.service.PaymentsService;
import de.nordakademie.util.ExceptionMessages;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * The type Payments controller.
 */
@RestController
@RequestMapping(path = "/rest/payments")
public class PaymentsController {
    /**
     * The Service.
     */
    private PaymentsService service;

    /**
     * Sets service.
     *
     * @param service the service
     */
    @Inject
    public void setService(PaymentsService service) {
        this.service = service;
    }

    /**
     * Find all payments list.
     *
     * @return the list
     */
    @GetMapping
    public List<Payments> findAllPayments() {
        return service.findAllPayments();
    }

    /**
     * Delete payment response entity.
     *
     * @param accountId the account id
     * @return the response entity
     * @throws DeleteFailedException the delete failed exception
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Payments> deletePayment(
            @PathVariable("id")
                    Long accountId) throws DeleteFailedException {
        try {
            service.deletePaymentsById(accountId);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            throw new DeleteFailedException(ExceptionMessages.PAYMENT_DELETE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch (EmptyResultDataAccessException ex) {
            // ToDo fafor: Der Text wird nicht in der Fehlermeldung angezeigt. Ggf. cause von "Exception" überschreiben?
            // Unterscheidung EntityNotFoundException und diese?
            throw new DeleteFailedException(ExceptionMessages.PAYMENT_NOT_FOUND_WHEN_DELETE, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Find payment by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ReadFailedException the read failed exception
     */
    @GetMapping("/{id}")
    public Optional<Payments> findPaymentById(
            @PathVariable("id")
                    Long id) throws ReadFailedException {
        try {
            return service.findPaymentsById(id);
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            throw new ReadFailedException(ExceptionMessages.PAYMENT_READ_FAILED, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create payments response entity.
     *
     * @param payments the payments
     * @return the response entity
     * @throws CreateFailedException the create failed exception
     */
    @PostMapping
    public ResponseEntity<Payments> createPayments(
            @Valid @RequestBody
                    Payments payments) throws CreateFailedException {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createPayments(payments));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            throw new CreateFailedException(ExceptionMessages.PAYMENT_CREATION_FAILED, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update payments response entity.
     *
     * @param id       the id
     * @param payments the payments
     * @return the response entity
     * @throws UpdateFailedException the update failed exception
     */
    @PutMapping("/{id}")
    public ResponseEntity<Payments> updatePayments(
            @PathVariable("id")
                    Long id,
            @Valid @RequestBody
                    Payments payments) throws UpdateFailedException {

        try {
            service.updatePayments(id, payments);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (IllegalArgumentException ex) {
            throw new UpdateFailedException(ExceptionMessages.PAYMENT_UPDATE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException ex) {
            throw new UpdateFailedException(ExceptionMessages.PAYMENT_NOT_FOUND_WHEN_UPDATE, HttpStatus.NOT_FOUND);
        }

    }

}
