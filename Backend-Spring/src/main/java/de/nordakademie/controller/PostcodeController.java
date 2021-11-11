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
import de.nordakademie.model.Postcode;
import de.nordakademie.service.PostcodeService;
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
                    Long id) {
        service.deletePostcodeById(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping
    public ResponseEntity<Postcode> createPostcode(
            @RequestBody
                    Postcode postcode) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createPostcode(postcode));
        } catch ( IllegalArgumentException ex ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public Optional<Postcode> findPostcodeById(
            @PathVariable("id")
                    Long id) {
        return service.findPostcodeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postcode> updatePostcode(
            @PathVariable("id")
                    Long id,
            @RequestBody
                    Postcode postcode) {
        try {
            service.updatePostcode(id, postcode);
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
