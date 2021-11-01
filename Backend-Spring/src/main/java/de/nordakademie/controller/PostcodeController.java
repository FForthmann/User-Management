package de.nordakademie.controller;


import de.nordakademie.model.Postcode;
import de.nordakademie.model.User;
import de.nordakademie.service.PostcodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rest/postcode")
public class PostcodeController {
    private PostcodeService service;

    @Inject
    public void setService(PostcodeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Postcode> findAllPostcodes(){
        return service.findAllPostcodes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Postcode> deletePostcode(@PathVariable("id") Long id){
        service.deletePostcodeById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Postcode> createPostcode(@RequestBody Postcode postcode){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPostcode(postcode));
    }

    @GetMapping("/{id}")
    public Optional<Postcode> findPostcodeById(@PathVariable("id") Long id) {
        return service.findPostcodeById(id);
    }

    @PutMapping
    public ResponseEntity<Postcode> updatePostcode(@RequestBody Postcode postcode){
        service.updatePostcode(postcode);
        return ResponseEntity.ok().build();
    }

}
