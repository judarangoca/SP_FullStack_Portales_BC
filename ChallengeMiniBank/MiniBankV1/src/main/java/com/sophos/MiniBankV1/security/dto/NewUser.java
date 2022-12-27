package com.sophos.MiniBankV1.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;
import com.sophos.MiniBankV1.security.entity.Role;

public class NewUser {
	@NotBlank //https://mvnrepository.com/artifact/javax.validation/validation-api/2.0.1.Final
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String name;
	@NotBlank
	private String surname;
	@Email
	private String email;
	
	private Set<String> roles = new HashSet<>(); //it allows us to agile the data-transfer of JSON

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	

}
