package com.dawes.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CodificadorConfig {
	@Bean
	public PasswordEncoder codificadorContraseña() {
		return new BCryptPasswordEncoder();
	}
}
