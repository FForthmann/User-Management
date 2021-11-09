package de.nordakademie.controller;

import de.nordakademie.model.MemberType;
import de.nordakademie.model.Postcode;
import de.nordakademie.service.MemberTypeService;
import de.nordakademie.service.PostcodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
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
    public List<MemberType> findAllMemberType(){
        return service.findAllMemberTypes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MemberType> deleteMemberType(@PathVariable("id") String memberTypeId){
        service.deleteMemberTypeById(memberTypeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Optional<MemberType> findMemberTypeById(@PathVariable("id") String id) {
        return service.findMemberTypeById(id);
    }

    @PostMapping
    public ResponseEntity<MemberType> createMemberType(@RequestBody MemberType memberType){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createMemberType(memberType));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberType> updateMemberType(@PathVariable("id") String id, @RequestBody MemberType memberType){
        try {
            service.updateMemberType(id, memberType);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
