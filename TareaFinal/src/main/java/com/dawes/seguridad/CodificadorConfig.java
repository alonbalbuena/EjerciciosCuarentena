package com.dawes.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CodificadorConfig {
	@Bean
	public PasswordEncoder codificadorContraseña() {
		//Contraseña cifrada
		return new BCryptPasswordEncoder();
		//contraseña sin cifrar
		//return NoOpPasswordEncoder.getInstance();
	}
}
