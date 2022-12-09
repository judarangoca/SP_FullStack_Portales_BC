package services;

import java.util.List;

import entities.Account;

public interface AccountService {
	
	public List<Account> GetAllAccountsById(int customerId);
	
	public Account ModifiyAccountBalance(int accountId);
	
	public Account CreateAccount();
	
	public boolean deleteAccountById(int accountId);
	

}
