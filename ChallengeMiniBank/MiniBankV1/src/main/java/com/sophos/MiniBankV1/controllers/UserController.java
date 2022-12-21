package com.sophos.MiniBankV1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.MiniBankV1.entities.Customer;
import com.sophos.MiniBankV1.entities.User;
import com.sophos.MiniBankV1.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService ;
	
	@GetMapping("") //Controlamos el metodo getAllUsers
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK); 
	}

}
