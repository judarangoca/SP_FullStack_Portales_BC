package com.sophos.MiniBankV1.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService ;
	
	@GetMapping("") //controlling method getAllUsers
	public ResponseEntity<Map<String, Object>> getUsers(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				
		logger.info("Datos del usuario: {}",auth.getPrincipal());
		logger.info("Datos de los permisos: {}",auth.getAuthorities());
		logger.info("Estado Autenticado {}",auth.isAuthenticated());
		
		Map<String, Object> response = new HashMap<>();
		
		
		response.put("contenido", userService.getAllUsers());
		response.put("mensaje", "Hola desde Perú");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); 
	}
	
	
	

}
