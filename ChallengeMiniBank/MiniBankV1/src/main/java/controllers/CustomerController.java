package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.Customer;
import services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("") //Controlamos el metodo getAllCustomers
	public ResponseEntity<List<Customer>> getCustomers(){
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK); 
	}
	
	@GetMapping("/{id}") //Controlamos el servicio getCustomerById
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") int id){
		return customerService.getCustomerById(id)
				.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping //Controlamos el servicio createCustomer
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> DeleteBook(@PathVariable("id") int id){ //OJO se puede quitar ee Object
		if (customerService.deleteCustomerById(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
