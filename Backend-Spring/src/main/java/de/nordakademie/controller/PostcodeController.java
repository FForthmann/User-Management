package de.nordakademie.controller;


import de.nordakademie.model.Postcode;
import de.nordakademie.service.PostcodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/postcode")
public class PostcodeController {
    private PostcodeService service;

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

    @PutMapping
    public ResponseEntity<Postcode> updatePostcode(@RequestBody Postcode postcode){
        service.updatePostcode(postcode);
        return ResponseEntity.ok().build();
    }

}
