package com.sophos.MiniBankV1.services;

import java.util.List;
import java.util.Optional;

import com.sophos.MiniBankV1.entities.Account;

public interface AccountService {
	
	public String GenerateNewAccountNumber(String accountType);	
	
	public boolean ValidateNewAccountNumber(String accountNumber);
	
	public Account CreateAccount(Account account);
	
	public List<Account> GetAllAccountsByCustomerId(int customerId);
			
	public Account EditAccount(Account account);
	
	public Optional<Account> GetAccountByAccountId(int accountId);
	
	public List<Account> GetAllAccounts();
	
	public boolean DeleteAccountByAccountId(int accountId);

}
