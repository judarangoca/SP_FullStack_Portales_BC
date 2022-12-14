package com.sophos.MiniBankV1.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.MiniBankV1.entities.Account;
import com.sophos.MiniBankV1.entities.Customer;
import com.sophos.MiniBankV1.security.jwt.JwtProvider;
import com.sophos.MiniBankV1.services.AccountService;
import com.sophos.MiniBankV1.services.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	@Autowired
	JwtProvider jwtProvider;
	
	@GetMapping("/")
	public ResponseEntity<List<Account>> getAccounts(){
		return new ResponseEntity<List<Account>>(accountService.GetAllAccounts(), HttpStatus.OK); 
	} 
	
	@GetMapping("/customerId{customerId}") //Controlamos el metodo getAllAccountsByCustomerId
	public ResponseEntity<List<Account>> getAccounts(@PathVariable("customerId") int customerId){
		
		return new ResponseEntity<List<Account>>(accountService.GetAllAccountsByCustomerId(customerId),
				HttpStatus.OK);
	}
	
	@GetMapping("/accountId{accountId}")
	public ResponseEntity<Account> getAccountByAccountId(@PathVariable("accountId") int accountId){
		
		Optional<Account> optionalAccount = accountService.GetAccountByAccountId(accountId);
		
		if (optionalAccount.isPresent()){
			return new ResponseEntity<Account>(optionalAccount.get(), HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//		OTRA MANERA
//				return accountService.GetAccountByAccountId(accountId)
//		.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
//		.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	
	@PostMapping //Controlamos el servicio createAccount
	public ResponseEntity<?> createAccount(@RequestBody Account account,
			@Valid @RequestHeader("Authorization") String token){
		
		if(token != null || !token.isBlank()) {
			String username = jwtProvider.getUsernameFromToken(token); 
			account.setModificationUser(username);
			account.setCreationUser(username);}	

		try {
			return new ResponseEntity<Account>(accountService.CreateAccount(account), HttpStatus.CREATED);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());}		
	}
	
	@PutMapping("/accountId{accountId}")
	public ResponseEntity<?> ModifiyAccount(@PathVariable("accountId") int accountId, 
			@RequestBody Account account,@Valid @RequestHeader("Authorization") String token){
		
		if(token != null || !token.isBlank()) {
			String username = jwtProvider.getUsernameFromToken(token); 
			account.setModificationUser(username);
			account.setCreationUser(username);}	

		try {
			return new ResponseEntity<Account>(accountService.EditAccount(account), HttpStatus.OK);	
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/accountId{accountId}")
	public ResponseEntity<Object> DeleteAccount(@PathVariable("accountId") int accountId){
		if (accountService.DeleteAccountByAccountId(accountId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
