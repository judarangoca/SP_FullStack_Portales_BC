package com.sophos.MiniBankV1.security.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.MiniBankV1.entities.Customer;
import com.sophos.MiniBankV1.security.entity.User;
import com.sophos.MiniBankV1.security.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService ;
	
	@GetMapping("") //controlling method getAllUsers
	public ResponseEntity<Map<String, Object>> getUsers(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				
		logger.info("User data: {}",auth.getPrincipal());
		logger.info("Authorities: {}",auth.getAuthorities());
		logger.info("Authentication Status {}",auth.isAuthenticated());
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("content", userService.getAllUsers());
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); 
	}
	

	@GetMapping("/userId{userId}")
	public ResponseEntity<?> getByUsername(@PathVariable(value = "userId") int userId){
		
		System.out.println("User ID: %s".formatted(userId));
		logger.info("User ID {}", userId);

		return userService.getUserbyUserId(userId).map(res->
			new ResponseEntity<>(res, HttpStatus.OK))
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/username{username}")
	public ResponseEntity<?> getByUsername(@PathVariable(value = "username") String username){
		return userService.getUserbyUsername(username).map(res->
			new ResponseEntity<>(res, HttpStatus.OK))
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/email{email}")
	public ResponseEntity<?> getByEmail(@PathVariable(value = "email") String email){
		return userService.getUserbyEmail(email).map(res->
			new ResponseEntity<>(res, HttpStatus.OK))
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}
	
	
	

}
