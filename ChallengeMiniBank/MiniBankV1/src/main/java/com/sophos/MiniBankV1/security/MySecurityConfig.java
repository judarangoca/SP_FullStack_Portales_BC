package com.sophos.MiniBankV1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sophos.MiniBankV1.security.jwt.JwtEntryPoint;
import com.sophos.MiniBankV1.security.jwt.previous.JwtAuthenticationFilter;
import com.sophos.MiniBankV1.security.service.UserService;
import com.sophos.MiniBankV1.security.service.UserServiceImplementation;

//import net.bytebuddy.agent.builder.AgentBuilder.Default.WarmupStrategy.NoOp;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig {//extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	private JwtAuthenticationEntryPoint unauthorizeHandler;
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private JwtAuthenticationFilter jwtAuthenticationFilter;
//	
//	@Override
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		
//		return super.authenticationManagerBean();		
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//	
//	
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(); //Podria cambiar el InMemory por una BD
//		manager.createUser(User.withUsername("admin")
//			.password(passwordEncoder().encode("admin"))
//			.roles()
//			.build());
//		
//		return manager;
//	}
//	
//	protected void configure( AuthenticationManagerBuilder authManagerBuilder) throws Exception {
//		authManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//	}
//
//	
//	
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http
//		.csrf().disable()
//		.cors().disable()
//		.authorizeRequests().antMatchers("/generate-token","/usuarios/").permitAll()
//		.antMatchers(HttpMethod.OPTIONS).permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.exceptionHandling().authenticationEntryPoint(unauthorizeHandler)
//		.and()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		
//		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//	}

}
