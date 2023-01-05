package com.sophos.MiniBankV1.security.jwt.previous;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sophos.MiniBankV1.security.entity.User;
import com.sophos.MiniBankV1.security.service.UserServiceImplementation;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter {//extends OncePerRequestFilter{
//	
//	@Autowired
//	private UserServiceImplementation userServiceImplementation;
//	@Autowired
//	private JwtUtils jwtUtils;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		String requestTokenHeader = request.getHeader("Authorization"); //obtenemos el header a partir de la peticion web (postman, cliente ..etc)
//		String username = null;
//		String jwtToken=null;
//		
//		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){ //La peticion para el token inicia con "Bearer "
//			
//			jwtToken = requestTokenHeader.substring(7); //quitamos el string "Bearer "
//			try {
//				username = this.jwtUtils.extractUsername(jwtToken);}
//			catch(ExpiredJwtException expiredJwtException) {
//				System.out.println("Token has expired ...");}
//			catch (Exception e) {
//				e.printStackTrace();}}
//		else {
//			System.out.println("Invalid Token. Check if the token starts with 'Bearer '");}
//	
//	
//		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			User user = userServiceImplementation.getUserbyUsername(username).get();
//			
//			if(this.jwtUtils.validateToken(jwtToken, user)) {
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
//						new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
//				
//				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
//			else {
//				System.out.println("Token results invalid ...");
//			}
//			
//			filterChain.doFilter(request, response);
//			
//		}
//	
//	
//	}
}
	