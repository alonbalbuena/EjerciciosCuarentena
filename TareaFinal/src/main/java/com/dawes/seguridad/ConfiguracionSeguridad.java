package com.dawes.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.dawes.servicios.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacionEntryPoint autenticacionEntryPoint;

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
		.antMatchers(HttpMethod.DELETE, "/proveedor/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/proveedor").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/","/inicio").hasAnyRole("USUARIO", "ADMIN")
		.antMatchers(HttpMethod.GET, "/proveedores", "/proveedor/**").hasAnyRole("USUARIO", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/proveedor/**").hasAnyRole("USUARIO", "ADMIN")
		.antMatchers(HttpMethod.POST, "/usuario").permitAll()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.and()
		.formLogin()
		.and()
		.csrf().disable()
			;
	}

}
