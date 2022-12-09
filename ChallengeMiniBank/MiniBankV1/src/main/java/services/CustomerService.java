package services;

import java.util.List;
import java.util.Optional;

import entities.Customer;

public interface CustomerService {
	
	//El servicio deberá crear un cliente que proviene del front
	public Customer createCustomer(Customer customer);
	
	//El servicio deberá listar todos los clientes para mostrarlos en el front
	public List<Customer> getAllCustomers();
	
	//El servicio deberá buscar un cliente por su ID
	public Optional<Customer> getCustomerById(int id);
	
	//El servicio deberá poder eliminar un cliente por su ID
	public boolean deleteCustomerById(int id);
	
}
