package com.dawes.seguridad;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AutenticacionEntryPoint extends BasicAuthenticationEntryPoint {

	@Autowired
	ObjectMapper mapeadorJson;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.addHeader("WWW-Authenticate", "Basic realm=\""+ getRealmName() + "\"");
		response.setContentType("applicacion/json");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		
		//mensaje de error
		PrintWriter writer = response.getWriter();
		writer.println("ERROR AUTENTICATION ENTRY POINT");
	}
	
	@PostConstruct//asignamos el nombre del reino
	public void initRealname() {
		setRealmName("serverAlon");
	}
}
