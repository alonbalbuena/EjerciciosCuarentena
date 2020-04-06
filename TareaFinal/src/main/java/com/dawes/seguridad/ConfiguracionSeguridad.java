package com.dawes.seguridad;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.dawes.servicios.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacionEntryPoint autenticacionEntryPoint;
	
	@Autowired
	private UsuarioServicio servicio;
	
	@Autowired
	private PasswordEncoder codificador;
	//nos permite escojer que tipo de peticiones no queremos que tengan seguridad
	//en este caso desavilitamos todo para trabajar
	//@Override
	//public void configure(WebSecurity web) throws Exception {
	//	web.ignoring().anyRequest();
	//}
	
	//AUTENTICACION
	@Override//autenticacion atraves del usuario de BBDD
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//le pasamos el loadUserByUsername implementado en el servicio
		auth.userDetailsService(servicio).passwordEncoder(codificador);
	}

	//AUTORIZACION
	@Override//permisos dados a peticione http
	//si nos da error 403(estamos correctamente autenticados pero no tenemos el ROL suficiente)
	protected void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic()
		.authenticationEntryPoint(autenticacionEntryPoint)
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/usuario").hasRole("USUARIO")
		.antMatchers(HttpMethod.GET,"/proveedores","/proveedor/**").hasRole("USUARIO")
		.antMatchers(HttpMethod.PUT,"/proveedor/**").hasRole("USUARIO")
		.antMatchers(HttpMethod.GET,"/usuario").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/proveedores","/proveedor/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/proveedor").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/proveedor/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/proveedor/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.csrf()
		.disable();
	}
	
}
