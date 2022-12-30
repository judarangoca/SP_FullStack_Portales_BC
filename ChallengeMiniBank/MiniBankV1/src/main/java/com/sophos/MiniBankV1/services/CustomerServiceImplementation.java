package com.sophos.MiniBankV1.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.MiniBankV1.entities.Account;
import com.sophos.MiniBankV1.entities.Customer;
import com.sophos.MiniBankV1.repository.AccountRepository;
import com.sophos.MiniBankV1.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository; //esto indica que la clase CustomerServiceImplementation tiene una propiedad del tipo CustomerRepository 
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Customer createCustomer(Customer customer){
		
		LocalDateTime birthDate = customer.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime nowDate = LocalDateTime.now();
		long yearsPeriod = birthDate.until(nowDate, ChronoUnit.YEARS);
		
		customer.setCreationDate(nowDate);
		customer.setModificationDate(nowDate);
		if(customer.getUserModificator() == null) {customer.setUserModificator("admin");}
		
		if (yearsPeriod < 18) {
			throw new RejectedExecutionException("Not possible to register an under-age customer");}
		
		//Pattern email filed validation
		Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (!pattern.matcher(customer.getEmail()).matches()) {
			throw new RejectedExecutionException("email fiel invalid");}
        
        if(customer.getFirstName().length()<=2 || customer.getLastName().length()<=2) {
        	throw new RejectedExecutionException("Name field or LastName-field invalid");
        }

		return customerRepository.save(customer);
		}

	
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(int id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id);
	}

	@Override
	public boolean deleteCustomerById(int id) {
		
		//Verifying all accounts are canceled
		ArrayList<Account> accounts = accountRepository.findByCustomerID(Integer.toString(id));
		boolean b = true;
		for (Account acc : accounts) {
			if(!acc.getAccountStatus().equals("Canceled")) {
				b=!b;
				throw new RejectedExecutionException("The customer owns accounts not canceled");}
		};
		
		if(b){
			return getCustomerById(id).map(customer -> {
				customerRepository.deleteById(id);
				return true;}).orElse(false);			
		}
		else {
			return false;
			}

	}

	@Override
	public Customer modifyCustomer(Customer customer) {
		customer.setModificationDate(LocalDateTime.now());
		
		if(customer.getUserModificator()==null || customer.getUserModificator().isBlank()) {
			customer.setUserModificator("admin");
		}
		
		customerRepository.save(customer);
		
		return customer;
	}

}
