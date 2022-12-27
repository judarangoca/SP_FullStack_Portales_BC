package com.sophos.MiniBankV1.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
	//This class will verify if the token is correct or not
	
	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class) ;
	
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		logger.error("fail in method commence");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "not authorized ...");
		
	}

}
