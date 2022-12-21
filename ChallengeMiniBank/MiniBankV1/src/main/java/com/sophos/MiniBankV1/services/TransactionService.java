package com.sophos.MiniBankV1.services;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import com.sophos.MiniBankV1.entities.Account;
import com.sophos.MiniBankV1.entities.Transaction;

public interface TransactionService {
		
	public Optional<List<Transaction>> getTransactionsByAccountId(int accountId);
		
	public Transaction Deposit(Transaction transaction);
	
	public Transaction Transfer(Transaction transaction);
	
	public Transaction Withdraw(Transaction transaction);

}
