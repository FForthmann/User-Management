package de.nordakademie.controller;

import de.nordakademie.model.Account;
import de.nordakademie.model.MemberType;
import de.nordakademie.model.Postcode;
import de.nordakademie.service.AccountService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rest/account")
public class AccountController {
    private AccountService service;

    @Inject
    public void setService(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<Account> findAllAccounts(){
        return service.findAllAccounts();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable("id") Long accountId ){
        service.deleteAccountById(accountId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Optional<Account> findAccountById(@PathVariable("id") Long id) {
        return service.findAccountById(id);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(account));
    }

    @PutMapping
    public ResponseEntity<Account> updateAccount(@RequestBody Account account){
        service.updateAccount(account);
        return ResponseEntity.ok().build();
    }

}
