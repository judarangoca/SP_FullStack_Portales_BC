package com.sophos.MiniBankV1.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sophos.MiniBankV1.security.entity.PrincipalUser;
import com.sophos.MiniBankV1.security.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.getUserbyUsername(username).get(); //The get method throws an exception in case the element is not found
		return PrincipalUser.build(user);
	}

}
