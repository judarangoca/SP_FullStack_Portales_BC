package com.sophos.MiniBankV1.security.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophos.MiniBankV1.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public Optional<User> findByUsername(String username);
	public Optional<User> findByEmail(String email);
	
	public boolean existsByUsername(String username); 
	public boolean existsByEmail(String email); 
	



}
