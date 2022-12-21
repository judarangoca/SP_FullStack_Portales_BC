package com.sophos.MiniBankV1.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.MiniBankV1.entities.Account;
import com.sophos.MiniBankV1.entities.Transaction;
import com.sophos.MiniBankV1.repository.AccountRepository;
import com.sophos.MiniBankV1.repository.TransactionRepository;

@Service
public class TransactionServiceImplementation implements TransactionService{
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Transaction Deposit(Transaction transaction) {
		
		int destAccId = transaction.getDestinationAccountId(); //destinyAccountId
		
		Optional<Account> destAcc = accountRepository.findById(destAccId);
		
		float value = transaction.getTransactionValue();
		destAcc.map(acc -> {
			acc.setAccountBalance(acc.getAccountBalance()+value);
			return accountRepository.save(acc);
		});
		
		transaction.setTypeOfTransaction("Deposit");
		transaction.setDestinationAccountId(destAccId);
		transaction.setOriginAccountId(99999999);
		transaction.setTransactionDate(LocalDateTime.now());
		
		transactionRepository.save(transaction);
		
		return transaction;
	}

	@Override
	public Transaction Transfer(Transaction transaction) {
		// TODO Auto-generated method stub
		int destAccId = transaction.getDestinationAccountId(); //destinyAccountId
		int origAccId = transaction.getOriginAccountId(); //originAccountId
		
		Optional<Account> destAcc = accountRepository.findById(destAccId);
		Optional<Account> origAcc = accountRepository.findById(origAccId);
		
		float value = transaction.getTransactionValue();
		
		destAcc.map(acc -> {
			acc.setAccountBalance(acc.getAccountBalance()+value);
			return accountRepository.save(acc);
		});
		
		origAcc.map(acc -> {
			acc.setAccountBalance(acc.getAccountBalance()-value);
			return accountRepository.save(acc);
		});
		
		transaction.setTypeOfTransaction("Transfer");
		transaction.setDestinationAccountId(destAccId);
		transaction.setOriginAccountId(origAccId);
		transaction.setTransactionDate(LocalDateTime.now());
		
		transactionRepository.save(transaction);
		
		return transaction;
	}

	@Override
	public Transaction Withdraw(Transaction transaction) {
		int origAccId = transaction.getOriginAccountId(); //originAccountId
		
		Optional<Account> origAcc = accountRepository.findById(origAccId);
		
		float value = transaction.getTransactionValue();
		
		origAcc.map(acc -> {
			acc.setAccountBalance(acc.getAccountBalance()-value);
			return accountRepository.save(acc);
		});
		
		transaction.setTypeOfTransaction("Withdraw");
		transaction.setDestinationAccountId(99999999);
		transaction.setOriginAccountId(origAccId);
		transaction.setTransactionDate(LocalDateTime.now());
		
		transactionRepository.save(transaction);
		
		return transaction;
	}

	@Override
	public Optional<List<Transaction>> getTransactionsByAccountId(int accountId) {
		
		String accountIdAsString = Integer.toString(accountId);
		Optional<List<Transaction>> transList = transactionRepository.getAllByAccountId(accountIdAsString);//metodo implementado
		return transList;
	}



}
