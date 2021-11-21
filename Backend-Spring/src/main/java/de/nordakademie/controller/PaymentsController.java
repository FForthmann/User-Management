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
@RestController
@RequestMapping(path = "/rest/payments")
public class PaymentsController {
    private PaymentsService service;

    @Inject
    public void setService(PaymentsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Payments> findAllPayments() {
        return service.findAllPayments();
    }

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
            // ToDo fafor: Der Text wird nicht in der Fehlermeldung angezeigt. Ggf. cause von "Exception" überschreiben?
            // Unterscheidung EntityNotFoundException und diese?
            throw new DeleteFailedException(ExceptionMessages.PAYMENT_NOT_FOUND_WHEN_DELETE, HttpStatus.NOT_FOUND);
        }
    }

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

    @PostMapping
    public ResponseEntity<Payments> createPayments(
            @Valid @RequestBody
                    Payments payments) throws CreateFailedException {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createPayments(payments));
        } catch ( IllegalArgumentException ex ) {
            ex.printStackTrace();
            throw new CreateFailedException(ExceptionMessages.PAYMENT_CREATION_FAILED, HttpStatus.BAD_REQUEST);
        }
    }

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
        } catch ( IllegalArgumentException ex ) {
            throw new UpdateFailedException(ExceptionMessages.PAYMENT_UPDATE_ILLEGAL_ARGUMENT, HttpStatus.BAD_REQUEST);
        } catch ( EntityNotFoundException ex ) {
            throw new UpdateFailedException(ExceptionMessages.PAYMENT_NOT_FOUND_WHEN_UPDATE, HttpStatus.NOT_FOUND);
        }

    }

}