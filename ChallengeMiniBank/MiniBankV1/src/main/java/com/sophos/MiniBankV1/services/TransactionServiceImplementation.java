package com.sophos.MiniBankV1.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;

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
		Optional<Account> optionalDestAcc = accountRepository.findById(destAccId);
		
		//Validating the account exists on the DB
		if (optionalDestAcc.isEmpty()) {
			throw new NoSuchElementException("Destination Account not Found");
		}
		
		//Validating the transactions match with a deposit
		if (! transaction.getTypeOfTransaction().equalsIgnoreCase("Deposit")) {
			throw new RejectedExecutionException("The declated transaction does not correspond to type Deposit");
		}
			
		float value = transaction.getTransactionValue();
		//Validating value of deposit
		if(value<=0) {
			throw new RejectedExecutionException("Incorrect Value for Transaction");
			}
		
		//Doing the modifications on the associated account
		Account destAcc = optionalDestAcc.get();
		destAcc.setAccountBalance(destAcc.getAccountBalance()+value);
			
		//Updating balance and current balance
		if (!destAcc.isExemptOfGmf()) {
			destAcc.setAccountCurrentBalance( (float)(destAcc.getAccountBalance()-0.004*destAcc.getAccountBalance()) );}
		else {
			destAcc.setAccountCurrentBalance( destAcc.getAccountBalance() );};

		//Updating modifications in account
		destAcc.setModificationDate(LocalDateTime.now());
		destAcc.setModificationUser("admin");
		accountRepository.save(destAcc);
		
		//Updating modifications in transaction
		transaction.setTypeOfTransaction("Deposit");
		transaction.setDestinationAccountId(destAccId);
		transaction.setOriginAccountId(99999999);
		transaction.setTransactionDate(LocalDateTime.now());
		transactionRepository.save(transaction);
		return transaction;
	}
	
	
	@Override
	public Transaction Withdraw(Transaction transaction) {
		
		int origAccId = transaction.getOriginAccountId(); //originAccountId
		Optional<Account> optionalOrigAcc = accountRepository.findById(origAccId);
		
		//Validating the account exists on the DB
		if (optionalOrigAcc.isEmpty()) {
			throw new NoSuchElementException("Origin Account not Found");
		}
		
		Account origAcc = optionalOrigAcc.get();
		
		//Validating the transactions match with a withdraw
		if (! transaction.getTypeOfTransaction().equalsIgnoreCase("Withdraw")) {
			throw new RejectedExecutionException("The declated transaction does not correspond to type Withdaw");
		}
		
		//Validating the account is in active status
		if (!origAcc.getAccountStatus().equalsIgnoreCase("Active")) {
			throw new RejectedExecutionException("The origin account is in a %s status".formatted(origAcc.getAccountStatus()));}
				
					
		//Validating the value of transaction
		float value = transaction.getTransactionValue();
		if(value<=0) {
			throw new RejectedExecutionException("Incorrect Value for Transaction");
			}
		
		//Validating the balances (validating the funds are enough)
		if( ( (origAcc.getAccountBalance() - value) < -3000000) && (origAcc.getAccountType().equalsIgnoreCase("Co")) ){
			throw new RejectedExecutionException("Insufficient funds for current account");}
		else if(  (origAcc.getAccountBalance() - value < 0) && (origAcc.getAccountType().equalsIgnoreCase("Ah")) ){
			throw new RejectedExecutionException("Insufficient founds for a saving account");}

		//Updating the balance and current balance
		origAcc.setAccountBalance(origAcc.getAccountBalance() - value);			
		if (!origAcc.isExemptOfGmf()) {
			origAcc.setAccountCurrentBalance((float) (origAcc.getAccountBalance() -0.004*origAcc.getAccountBalance()));}
		else {
			origAcc.setAccountCurrentBalance( origAcc.getAccountBalance() );};
				
		//Updating the modifications into account
		origAcc.setModificationDate(LocalDateTime.now());
		origAcc.setModificationUser("admin");
		accountRepository.save(origAcc);

		//Updating the transaction
		transaction.setTypeOfTransaction("Withdraw");
		transaction.setDestinationAccountId(99999999);
		transaction.setTransactionDate(LocalDateTime.now());
		transactionRepository.save(transaction);
		return transaction;
	}

	@Override
	public Transaction Transfer(Transaction transaction) {
		// TODO Auto-generated method stub
		int destAccId = transaction.getDestinationAccountId(); //destinyAccountId
		int origAccId = transaction.getOriginAccountId(); //originAccountId
		
		Optional<Account> optionalDestAcc = accountRepository.findById(destAccId);
		Optional<Account> optionalOrigAcc = accountRepository.findById(origAccId);

		//Validating the accounts existence on DB
		if (optionalOrigAcc.isEmpty()) {
			throw new NoSuchElementException("Origin Account not Found");}
		if (optionalDestAcc.isEmpty()) {
			throw new NoSuchElementException("Destination Account not Found");}
		
		Account origAcc = optionalOrigAcc.get();
		Account destAcc = optionalDestAcc.get();
		
		//Validating the transaction declared corresponds to a transfer
		if (! transaction.getTypeOfTransaction().equalsIgnoreCase("Transfer")) {
			throw new RejectedExecutionException("The declated transaction does not correspond to type Transfer");}
		
		//validamos que el valor de la transacción sea valido
		float value = transaction.getTransactionValue();
		if(value<=0) {
			throw new RejectedExecutionException("Incorrect Value for Transaction");};
		
		//Validamos que la cuenta de origen no se encuentre inactiva
		if (!origAcc.getAccountStatus().equalsIgnoreCase("Activa")) {
			throw new RejectedExecutionException("The origin account is in a %s status".formatted(origAcc.getAccountStatus()));}
			
		//validamos que los balances de la cuenta de origen cumplan con los requisitos
		if( ( (origAcc.getAccountBalance() - value) < -3000000) && (origAcc.getAccountType().equalsIgnoreCase("Co")) ){
			throw new RejectedExecutionException("Insufficient funds for current account");}
		else if(  (origAcc.getAccountBalance() - value < 0) && (origAcc.getAccountType().equalsIgnoreCase("Ah")) ){
			throw new RejectedExecutionException("Insufficient funds for saving account");}

		//Actualizamos el balance de la cuenta de origen
		origAcc.setAccountBalance(origAcc.getAccountBalance() - value);
		//Actualizamos el saldo corriente de la cuenta de origen
		if (!origAcc.isExemptOfGmf()) {
			origAcc.setAccountCurrentBalance( (float)(origAcc.getAccountBalance()-0.004*origAcc.getAccountBalance()) );}
		else {
			origAcc.setAccountCurrentBalance( origAcc.getAccountBalance() );};
		//Actualizamos las modificaciones en la cuenta de origen
		origAcc.setModificationDate(LocalDateTime.now());
		origAcc.setModificationUser("admin");
		accountRepository.save(origAcc);
		
		
		//Actualizamos el balance de la cuenta destino
		destAcc.setAccountBalance(destAcc.getAccountBalance() + value);
		//Actualizamos el saldo corriente de la cuenta destino
		if (!destAcc.isExemptOfGmf()) {
			destAcc.setAccountCurrentBalance( (float)(destAcc.getAccountBalance()-0.004*destAcc.getAccountBalance()) );}
		else {
			destAcc.setAccountCurrentBalance( destAcc.getAccountBalance() );};
		//Actualizamos las modificaciones en la cuenta de origen
		destAcc.setModificationDate(LocalDateTime.now());
		destAcc.setModificationUser("admin");
		accountRepository.save(destAcc);

		///////////////////////////////////////////////////////////////////////////
		
		transaction.setTypeOfTransaction("Transfer");
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
