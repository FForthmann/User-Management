package de.nordakademie.controller;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
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
import de.nordakademie.model.Payments;
import de.nordakademie.service.PaymentsService;
@RestController
@RequestMapping(path = "/rest/payments")
public class PaymentsController {
    private PaymentsService service;

    @Inject
    public void setService(PaymentsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Payments> findAllAccounts() {
        return service.findAllPayments();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Payments> deleteAccount(
            @PathVariable("id")
                    Long accountId) {
        service.deletePaymentsById(accountId);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{id}")
    public Optional<Payments> findAccountById(
            @PathVariable("id")
                    Long id) {
        return service.findPaymentsById(id);
    }

    @PostMapping
    public ResponseEntity<Payments> createAccount(
            @RequestBody
                    Payments payments) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createPayments(payments));
        } catch ( IllegalArgumentException ex ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payments> updateAccount(
            @PathVariable("id")
                    Long id,
            @RequestBody
                    Payments payments) {

        try {
            service.updatePayments(id, payments);
            return ResponseEntity
                    .ok()
                    .build();
        } catch ( IllegalArgumentException ex ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch ( EntityNotFoundException ex ) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }

}
