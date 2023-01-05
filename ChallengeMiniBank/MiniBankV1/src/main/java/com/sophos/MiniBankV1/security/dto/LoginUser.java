package com.sophos.MiniBankV1.security.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

public class LoginUser {
	
	@NotBlank
	private String username;
	@NotBlank
	private String password;
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
	
	

}
