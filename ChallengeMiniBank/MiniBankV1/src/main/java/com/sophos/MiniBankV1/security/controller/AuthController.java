package com.sophos.MiniBankV1.security.controller;

import java.awt.PageAttributes.MediaType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Pattern;

import javax.print.attribute.HashAttributeSet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.MiniBankV1.security.dto.JwtDto;
import com.sophos.MiniBankV1.security.dto.LoginUser;
import com.sophos.MiniBankV1.security.dto.NewUser;
import com.sophos.MiniBankV1.security.entity.Role;
import com.sophos.MiniBankV1.security.entity.User;
import com.sophos.MiniBankV1.security.enums.RoleNames;
import com.sophos.MiniBankV1.security.jwt.JwtProvider;
import com.sophos.MiniBankV1.security.jwt.previous.JwtRequest;
import com.sophos.MiniBankV1.security.jwt.previous.JwtResponse;
import com.sophos.MiniBankV1.security.jwt.previous.JwtUtils;
import com.sophos.MiniBankV1.security.service.RoleServiceImplementation;
import com.sophos.MiniBankV1.security.service.UserService;
import com.sophos.MiniBankV1.security.service.UserServiceImplementation;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserService userServiceImplementation;
	@Autowired
	RoleServiceImplementation roleServiceImplementation;
	@Autowired
	JwtProvider jwtProvider;
	
	@PreAuthorize("hasRole('ADMIN')") //with this injection, only Users with admin role could create newuser
	@PostMapping("/newUser")
	public ResponseEntity<?> newUser (@Valid @RequestBody NewUser newUser,
			BindingResult bindingResult) throws Exception{ //BindingResult allow us to validate the newUser JSON Object

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("invalid new user fields",HttpStatus.BAD_REQUEST);}
		
		if (userServiceImplementation.existsByUserName(newUser.getUsername())) {
			return new ResponseEntity<>("username already used",HttpStatus.BAD_REQUEST);}
		
		if (userServiceImplementation.existsByEmail(newUser.getEmail())) {
			return new ResponseEntity<>("email already used",HttpStatus.BAD_REQUEST);}
		
		//Pattern para la validación del campo email
		Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (!pattern.matcher(newUser.getEmail()).matches()) {
        	return new ResponseEntity<>("email fiel invalid",HttpStatus.BAD_REQUEST);}
		
			
		User user = new User(newUser.getUsername(),
				passwordEncoder.encode(newUser.getPassword()),
				newUser.getName(), newUser.getSurname(), newUser.getEmail());

		Set<Role> roles = new HashSet<>();
				
		roles.add( roleServiceImplementation.findByRoleName(RoleNames.ROLE_USER.name()).get() );
		if(newUser.getRoles().contains("admin")) {
            roles.add(roleServiceImplementation.findByRoleName(RoleNames.ROLE_ADMIN.name()).get()) ;}
        
		user.setRoles(roles);
		
		userServiceImplementation.save(user);
		
		HashMap<String, String> response = new HashMap<>();
		response.put("message","User saved correctly");

		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity("invalid user",HttpStatus.BAD_REQUEST);}
		
		Authentication authentication = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails =  (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}
	
//	
//	@PostMapping(path="/generate-token")
//	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
//		
//		
//		try {
//			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
//		} catch (Exception exception) {
//			exception.printStackTrace();
//			throw new Exception("User not found ...");
//		}
//		
//		User user = userServiceImplementation.getUserbyUsername(jwtRequest.getUsername()).get();
//		String token = this.jwtUtils.generateToken(user);
//		
//		return ResponseEntity.ok(new JwtResponse(token));
//
//	}
//	
//
//	private void authenticate(String username, String password) throws Exception {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException disabledException) {
//			throw new Exception("DISABLED USER ... " + disabledException.getMessage());
//		} catch (BadCredentialsException badCredentialsException) {
//			throw new Exception("INVALID CREDENTIALS ..."+ badCredentialsException);
//		}
//	}

}
