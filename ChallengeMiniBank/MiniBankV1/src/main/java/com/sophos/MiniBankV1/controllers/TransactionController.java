package com.sophos.MiniBankV1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.MiniBankV1.entities.Account;
import com.sophos.MiniBankV1.entities.Transaction;
import com.sophos.MiniBankV1.services.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {
	
	private static final HttpStatus ResponseEntity = null;
	@Autowired
	TransactionService transactionService; 
	
	@PostMapping("/deposit") 
	public ResponseEntity<Transaction> depositTransaction(@RequestBody Transaction transaction){
		return new ResponseEntity<>(transactionService.Deposit(transaction), HttpStatus.CREATED);
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<Transaction> transferTransaction(@RequestBody Transaction transaction){
		return new ResponseEntity<>(transactionService.Transfer(transaction), HttpStatus.CREATED);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Transaction> withdrawTransaction(@RequestBody Transaction transaction){
		return new ResponseEntity<>(transactionService.Withdraw(transaction), HttpStatus.CREATED);
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
