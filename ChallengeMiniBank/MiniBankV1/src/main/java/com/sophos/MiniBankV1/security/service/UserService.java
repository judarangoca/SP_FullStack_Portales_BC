package com.sophos.MiniBankV1.security.service;

import java.util.List;
import java.util.Optional;

import com.sophos.MiniBankV1.security.entity.User;

public interface UserService{
	
	public List<User> getAllUsers();
	
	public Optional<User> getUserbyUserId(int userId);
	
	public Optional<User> getUserbyUsername(String username);
	
	public boolean existsByUserName(String usename);
	
	public Optional<User> getUserbyEmail(String email);
	
	public boolean existsByEmail(String email);
	
	public void save(User user);
}
