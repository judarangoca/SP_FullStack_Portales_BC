package com.sophos.MiniBankV1.security.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.MiniBankV1.security.entity.User;
import com.sophos.MiniBankV1.security.repository.UserRepository;

@Service
@Transactional
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserbyUserId(int userId){
		return userRepository.findById(Integer.valueOf(userId));
	}
	
	public Optional<User> getUserbyUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public boolean existsByUserName(String usename) {
		return userRepository.existsByUsername(usename);
	}
	
	public Optional<User> getUserbyEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
    public void save(User user){
        userRepository.save(user);
    }


}
