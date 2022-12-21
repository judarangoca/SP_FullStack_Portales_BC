package com.sophos.MiniBankV1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.MiniBankV1.entities.Account;
import com.sophos.MiniBankV1.repository.AccountRepository;

@Service
public class AccountServiceImplementation implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public String GenerateNewAccountNumber(String accountType) {
		String stringOutput = "";
		if(accountType.equals("Co")){
			stringOutput = "23" ;}
		else if(accountType.equals("Ah")){
			stringOutput = "46";}
		
		for(int ii=1 ; ii <= 8 ; ii = ii + 1){
			Random random = new Random();
			int digit = random.nextInt(10); //this creates a random digit with values 0,1,2...9
			stringOutput = stringOutput + Integer.toString(digit);
		}
		return stringOutput;
	}
	
	@Override
	public boolean ValidateNewAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		//Validamos que la cuenta no exista previamente en la BD
		List<Account> listExistingAccountNumbers = accountRepository.findAll();
		boolean validation;
		validation = ! listExistingAccountNumbers.contains(accountNumber);
		return validation;
	}
	
	public boolean ValidateGmf(int customerId) {
		return false;
	}
	
	@Override
	public Account CreateAccount(Account account) {
		// TODO Auto-generated method stub
		boolean validation = false;
		String newAccountNumber="";
		
		while (!validation){
			newAccountNumber=GenerateNewAccountNumber(account.getAccountType());	
			validation = ValidateNewAccountNumber(newAccountNumber); //cuando validation=true rompe el ciclo
		}
		
		account.setAccountNumber(newAccountNumber);
		account.setAccountStatus("Activa");
		return accountRepository.save(account);
		}

	@Override
	public List<Account> GetAllAccountsByCustomerId(int customerId) {
//		Optional<Account> accountsList = accountRepository.findById(customerId);
		
//		List<Account> results = new ArrayList<Account>();
//		accountRepository.findById(customerId).ifPresent(results::add);
//		return results;
		
//		List<Integer> iterableId = new ArrayList<Integer>();
//		iterableId.add(Integer.valueOf(customerId));
//		return accountRepository.findAllById(iterableId);
		
		//return (List<Account>)accountRepository.findByKeyword(Integer.toString(customerId));
		String stringCustomerId = Integer.toString(customerId);
		return accountRepository.findByCustomerID(stringCustomerId);
	}

	@Override
	public Account EditAccount(Account account) {
		// TODO Auto-generated method stub
			return accountRepository.save(account);
	}
	
	public Optional<Account> GetAccountByAccountId(int accountId){
		return accountRepository.findById(accountId);
	}

	@Override
	public boolean DeleteAccountByAccountId(int accountId){

		Optional<Account> account = accountRepository.findById(accountId);
		if (account.isPresent()) {
			accountRepository.deleteById(accountId);
			return true;
		}
		else{
			return false;}

		}

	@Override
	public List<Account> GetAllAccounts() {
		return accountRepository.findAll();
	};
	
	


}


