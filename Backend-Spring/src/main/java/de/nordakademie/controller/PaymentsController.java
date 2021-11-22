package de.nordakademie.controller;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
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
import de.nordakademie.model.Payments;
import de.nordakademie.service.PaymentsService;
import de.nordakademie.util.ExceptionMessages;
/**
 * The Spring Controller for Payments.
 *
 * @author Ridvan Cetin & Fabian Forthmann
 */
@RestController
@RequestMapping(path = "/rest/payments")
public class PaymentsController {
    /**
     * The Service for processing payment requests.
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
     * Researches all payments and returns them as a list.
     *
     * @return the list
     */
    @GetMapping
    public List<Payments> findAllPayments() {
        return service.findAllPayments();
    }

    /**
     * Searches a payment by its id and returns it.
     *
     * @param id the invoice number
     * @return the payment or an empty optional object
     * @throws ReadFailedException finding the payment failed due to a specific failure
     */
    @GetMapping("/{id}")
    public Optional<Payments> findPaymentById(
            @PathVariable("id")
                    Long id) throws ReadFailedException {
        try {
            return service.findPaymentsById(id);
        } catch ( EntityNotFoundException ex ) {
            ex.printStackTrace();
            throw new ReadFailedException(ExceptionMessages.PAYMENT_READ_FAILED, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a payment.
     *
     * @param payment the payment object
     * @return the created payment as a response entity
     * @throws CreateFailedException creating the payment failed due to a specific failure
     */
    @PostMapping
    public ResponseEntity<Payments> createPayments(
            @Valid
            @RequestBody
                    Payments payment) throws CreateFailedException {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createPayments(payment));
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new CreateFailedException(ExceptionMessages.PAYMENT_CREATION_FAILED, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a payment by its id.
     *
     * @param accountId the payment id
     * @return an entity object with an HTTP status
     * @throws DeleteFailedException deleting the payment failed due to a specific failure
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
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new DeleteFailedException(ExceptionMessages.PAYMENT_DELETE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch ( EmptyResultDataAccessException ex ) {
            throw new DeleteFailedException(ExceptionMessages.PAYMENT_NOT_FOUND_WHEN_DELETE, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates a payment.
     *
     * @param id the payment id
     * @param payment the user object
     * @return the updated user as a response entity
     * @throws UpdateFailedException updating the user failed due to a specific failure
     */
    @PutMapping("/{id}")
    public ResponseEntity<Payments> updatePayments(
            @PathVariable("id")
                    Long id,
            @Valid
            @RequestBody
                    Payments payment) throws UpdateFailedException {
        try {
            service.updatePayments(id, payment);
            return ResponseEntity
                    .ok()
                    .build();
        } catch ( IllegalArgumentException ex ) {
            throw new UpdateFailedException(ExceptionMessages.PAYMENT_UPDATE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch ( EntityNotFoundException ex ) {
            throw new UpdateFailedException(ExceptionMessages.PAYMENT_NOT_FOUND_WHEN_UPDATE, HttpStatus.NOT_FOUND);
        }
    }
}
