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
	public Optional<Customer> createCustomer(Customer customer){
		
		LocalDateTime birthDate = customer.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime nowDate = LocalDateTime.now();
		long yearsPeriod = birthDate.until(nowDate, ChronoUnit.YEARS);
		
		Optional<Customer> opt;
		if (yearsPeriod > 18) {
			opt = Optional.of(customerRepository.save(customer));}
		else {			
			opt = Optional.empty();
			throw new RejectedExecutionException("El cliente registrado es menor de edad");}
		return opt;
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
		
		//Verificamos que el cliente no tenga ninguna cuenta en estado bloqueada!
		ArrayList<Account> accounts = accountRepository.findByCustomerID(Integer.toString(id));
		boolean b = true;
		for (Account acc : accounts) {
			if( acc.getAccountStatus().equals("Bloqueada")) {
				b=!b;
				throw new RejectedExecutionException("El usuario tiene una de sus cuentas bloqueadas");}
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
		customerRepository.save(customer);
		return null;
	}

}
