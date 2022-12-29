package com.sophos.MiniBankV1.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.aspectj.weaver.ast.Instanceof;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.sophos.MiniBankV1.entities.Customer;
import com.sophos.MiniBankV1.security.controller.UserController;
import com.sophos.MiniBankV1.security.jwt.JwtProvider;
import com.sophos.MiniBankV1.services.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomerController{
	
	protected static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	JwtProvider jwtProvider;
	
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
	public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer,
			@Valid @RequestHeader("Authorization") String token){
			//@RequestHeader Map<String,String> headers){
		
		if(token != null || !token.isBlank()) {
			String username = jwtProvider.getUsernameFromToken(token); 
			customer.setUserModificator(username);}	
		try {
			Customer customerCreated = customerService.createCustomer(customer);
			return new ResponseEntity<Customer>(customerCreated,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> DeleteCustomer(@PathVariable("id") int id){
		try {
			return customerService.deleteCustomerById(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Object> editCustomer(@RequestBody Customer customer,
			@PathVariable("id") int id, @Valid @RequestHeader("Authorization") String token){
		
		if(token != null || !token.isBlank()) {
			String username = jwtProvider.getUsernameFromToken(token); 
			customer.setUserModificator(username);}	
		
		customer.setCustomerId(id);
		
		try {
			return new ResponseEntity<>(customerService.modifyCustomer(customer), HttpStatus.OK );
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}

}
