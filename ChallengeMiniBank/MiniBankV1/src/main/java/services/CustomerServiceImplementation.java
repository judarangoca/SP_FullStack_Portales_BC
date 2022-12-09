package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Customer;
import repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository; //esto indica que la clase CustomerServiceImplementation tiene una propiedad del tipo CustomerRepository 

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
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
		return getCustomerById(id).map(customer -> {
			customerRepository.deleteById(id);
			return true;}).orElse(false);
	}

}
