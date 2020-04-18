package com.dawes.ws;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomRestTemplate {

	/*CREAMOS UN BEAN CUSTOM PARA PODER INYECTARLA CON AUTOWIRE EN EL SERVICIO*/
	@Bean
	public RestTemplate crearRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
