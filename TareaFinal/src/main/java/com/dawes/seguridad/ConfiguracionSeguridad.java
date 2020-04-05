package com.dawes.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	//nos permite escojer que tipo de peticiones no queremos que tengan seguridad
	//en este caso desavilitamos todo para trabajar
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().anyRequest();
	}
	
	@Bean
	public PasswordEncoder codificadorContraseña() {
		return new BCryptPasswordEncoder();
	}
}
