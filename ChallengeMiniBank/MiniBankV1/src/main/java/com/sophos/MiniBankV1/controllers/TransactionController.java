package com.sophos.MiniBankV1.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.MiniBankV1.entities.Account;
import com.sophos.MiniBankV1.entities.Transaction;
import com.sophos.MiniBankV1.security.jwt.JwtProvider;
import com.sophos.MiniBankV1.services.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService; 
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/deposit") 
	public ResponseEntity<Object> depositTransaction(@RequestBody Transaction transaction){
		try {
			return new ResponseEntity<>(transactionService.Deposit(transaction), HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<Object> transferTransaction(@RequestBody Transaction transaction){

		try {
			return new ResponseEntity<Object>(transactionService.Transfer(transaction), HttpStatus.CREATED);}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}		
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<?> withdrawTransaction(@RequestBody Transaction transaction){
		try {
			return new ResponseEntity<Transaction>(transactionService.Withdraw(transaction), HttpStatus.CREATED);}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
			
	}
	
	@GetMapping(path = "/accountId{accountId}")
	public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable("accountId") int accountId){
		
		Optional<List<Transaction>> accounts; 
		accounts = transactionService.getTransactionsByAccountId(accountId);
		
		if(accounts.isPresent()){
			return new ResponseEntity<List<Transaction>>(accounts.get(), HttpStatus.OK);}
		else {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
}
