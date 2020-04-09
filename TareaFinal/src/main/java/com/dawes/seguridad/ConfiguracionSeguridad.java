package com.dawes.seguridad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.dawes.servicios.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioServicio servicioUsuario;

	@Autowired
	private PasswordEncoder codificador;

	@Override // permitimos el uso de medios estaticos como paginas de estilos fuentes y JS
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/fonts/**", "/js/**");
	}

	// AUTENTICACION
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// le pasamos el loadUserByUsername implementado en el servicioUsuario
		// autenticacion con los usuarios usados en la BBDD
		auth.userDetailsService(servicioUsuario).passwordEncoder(codificador);
		// autenticacion con el usuario definido aqui
		// auth.inMemoryAuthentication().withUser("blah").password("blah").roles("USUARIO");
	}

	// AUTORIZACION

	@Override // permisos dados a peticione http //si nos da error 403(estamos
	//correctamente autenticado pero no tenemos el ROL suficiente)
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.DELETE,"/proveedor/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/proveedor").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/user/**").hasRole("USUARIO")
		.antMatchers(HttpMethod.GET, "/proveedores", "/proveedor/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/proveedor/**").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario").permitAll()
		.antMatchers(HttpMethod.GET, "/yo").permitAll()
		.and()
		.formLogin()
		.loginPage("/logeo")//la pagina a la que redirecciona
		.loginProcessingUrl("/autentificacion")//la direccion que va en action del index
		/*el controlador con la ruta "/perform_login" no lo hemos configurado nosotrs
		 * si no que es SPring el que se encarga de todo ello, simepre que usemos el
		 * nombre de los campos del formulario siguiendo las convenciones de SPring
		 * Ej: 
		 * (importante tambien que el metodo sea POST y que action sea la de "loginProcessingUrl"
		 * <input type="text" name="username" id="usuario" />
		 * <input type="text" name="password" id="contraseña" />*/
		.defaultSuccessUrl("/user/inicio")//a que pagina redirige
		.failureUrl("/logeo")//a que pagina redirige si hay fallo
		.permitAll()
		.and()
		.csrf().disable();
	}
	
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		RestTemplate plantilla = restTemplateBuilder.basicAuthentication("admin", "admin").build();
		
		//indico que tipos de contenido son permitidos como respuesta a las peticiones RESTTEMPLATE
		//(en este caso permito todos)
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		   MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		   converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		   messageConverters.add(converter);
		   plantilla.setMessageConverters(messageConverters);
		return plantilla;
	}

}
