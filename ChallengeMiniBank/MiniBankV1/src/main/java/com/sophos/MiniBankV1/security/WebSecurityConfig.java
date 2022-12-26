package com.sophos.MiniBankV1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
			
		return http
				.csrf().disable()
				.authorizeRequests() //ver minuto 13:00 https://www.youtube.com/watch?v=ckNz6ITf16E
				.anyRequest().authenticated()
				.and()
				.httpBasic()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.build();
		}
	
	@Bean
	UserDetailsService userDetailsService() {
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(); //Podria cambiar el InMemory por una BD
		manager.createUser(User.withUsername("admin")
			.password(passwordEncoder().encode("admin"))
			.roles()
			.build());
		
		return manager;
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //it could be used anoted crypting implementation
	}
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService())
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}

}
