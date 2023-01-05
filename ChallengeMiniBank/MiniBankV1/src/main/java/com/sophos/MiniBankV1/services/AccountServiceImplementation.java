package com.sophos.MiniBankV1.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

import com.sophos.MiniBankV1.entities.Account;
import com.sophos.MiniBankV1.entities.Customer;
import com.sophos.MiniBankV1.repository.AccountRepository;
import com.sophos.MiniBankV1.repository.CustomerRepository;

@Service
public class AccountServiceImplementation implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public String GenerateNewAccountNumber(String accountType) {
		String newAccountNumber="";
		if(accountType.equals("Co")){
			newAccountNumber = "23" ;}
		else if(accountType.equals("Ah")){
			newAccountNumber = "46";}
		
		for(int ii=1 ; ii <= 8 ; ii = ii + 1){
			Random random = new Random();
			int digit = random.nextInt(10); //this creates a random digit with values 0,1,2...9
			newAccountNumber = newAccountNumber + Integer.toString(digit);}
		return newAccountNumber;
	}
	
	@Override
	public boolean ValidateNewAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		//Validating the account does not exist previously on the DB
		List<Account> listExistingAccountNumbers = accountRepository.findAll();
		boolean validation;
		validation = ! listExistingAccountNumbers.contains(accountNumber);
		return validation;
	}
	
	
	@Override
	public Account CreateAccount(Account account) {

		//Validating the account does not exist previously on the DB
		boolean validateAccountNumber = false;
		String newAccountNumber="";
		while (!validateAccountNumber){
			newAccountNumber=GenerateNewAccountNumber(account.getAccountType());	
			validateAccountNumber = ValidateNewAccountNumber(newAccountNumber);} //cuando validateAccountNumber=true rompe el ciclo
		
		//Validating getAccountType() matchs "Ah" or "Co"
		if( !account.getAccountType().equalsIgnoreCase("Ah") || !account.getAccountType().equalsIgnoreCase("Co") ){
			throw new RejectedExecutionException("The account should be an Ac account or a Co account");}

		//Validating customerId exists in the DB.		
		Optional<Customer> customerAssociated = customerRepository.findById(account.getCustomerId()); //Obtenemos el Customer asociado
		if (customerAssociated.isEmpty()) {
			throw new NoSuchElementException("The customer Id is not fund");}			
	
		account.setAccountNumber(newAccountNumber);
		account.setAccountStatus("Active");
		
		if(account.getModificationUser()==null || account.getModificationUser().isBlank()) {
			account.setModificationUser("admin");
		}
		if(account.getCreationUser()==null || account.getCreationUser().isBlank()) {
			account.setCreationUser("admin");
		}
		account.setModificationDate(LocalDateTime.now());
		account.setAccountCreationDate(LocalDateTime.now());
		account.setAccountBalance(0);
		account.setAccountCurrentBalance(0);
		account.setExemptOfGmf(false);
		
		return accountRepository.save(account);
		}

	@Override
	public List<Account> GetAllAccountsByCustomerId(int customerId) {

		String stringCustomerId = Integer.toString(customerId);
		return accountRepository.findByCustomerID(stringCustomerId);
	}

	@Override
	public Account EditAccount(Account account) {
		
		int accountId = account.getAccountId();
		Optional<Account> optionalCurrentAccount = accountRepository.findById(accountId);
		Account currentAccount = optionalCurrentAccount.get();
		
		//Validating the account does not exist previously on the DB
		if (optionalCurrentAccount.isEmpty()) {
			throw new NoSuchElementException(String.format("The account with Id %s is not found", accountId));
		}

		//Validamos que la cuenta no se pueda "cancelar" a menos que no tenga deudas ni saldo disponible
		if(account.getAccountStatus().equalsIgnoreCase("Cancelada")) {
			boolean b = currentAccount.getAccountBalance()<0 || currentAccount.getAccountBalance()>1;
			if( b ) {
				throw new RejectedExecutionException("The account owns pending balance. It is not possible to cancel the account");};
		};
		
		//Validamos que solo exista una cuenta exenta del GMF
		int currentAccountId = currentAccount.getCustomerId();
		List<Account> listOfAccounts = accountRepository.findByCustomerID(Integer.toString(currentAccountId));
		List<Boolean> gmfStatusList = listOfAccounts.stream().map(Account::isExemptOfGmf).collect(Collectors.toList());
		if(account.isExemptOfGmf() && gmfStatusList.contains(true)) {
			throw new RejectedExecutionException("The customer associated owns an excempt GMF 0.4% account already");
		};
		
		account.setModificationDate(LocalDateTime.now());
		if(account.getModificationUser()==null || account.getModificationUser().isBlank()) {
			account.setModificationUser("admin");
		}
		return accountRepository.save(account);
	}
	
	public Optional<Account> GetAccountByAccountId(int accountId){
		return accountRepository.findById(accountId);
	}

	@Override
	public boolean DeleteAccountByAccountId(int accountId){

		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		
		if (optionalAccount.isEmpty()) {
			throw new NoSuchElementException("The account with Id %s is not found".formatted(accountId));
		}
		
		Account account = optionalAccount.get();
		
		if (!(account.getAccountStatus().equalsIgnoreCase("canceled"))) {
			
			throw new RequestRejectedException(
					"Account with accound Id %s is not in canceled status".formatted(account.getAccountId()));
		}
		else{
			accountRepository.deleteById(accountId);
			return true;}
		}

	@Override
	public List<Account> GetAllAccounts() {
		return accountRepository.findAll();
	};
	
	


}


