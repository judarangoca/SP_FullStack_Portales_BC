package com.sophos.MiniBankV1.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.MiniBankV1.security.entity.Role;
import com.sophos.MiniBankV1.security.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImplementation {
	
	@Autowired
	RoleRepository roleRepository;

	public Optional<Role> findByRoleName(String roleName){
		return roleRepository.findByRoleName(roleName);
	}
	
	public void save(Role role) {
		roleRepository.save(role);
	}

}
