package com.dawes.servicios;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dawes.modelo.Pais;
import com.dawes.modelo.Resultado;
import com.google.gson.Gson;

@Service
public class Servicio {

	private static Resultado ultimoResultado;//llamamos a este resultado desde la API
	
	public Resultado getUltimoResultado() {
		return ultimoResultado;
	}
	
	public Integer getCasosTotales() {
		List<Pais> resultados = getUltimoResultado().getResults();
		return resultados.stream().map(resultado -> resultado.getCurrentConfirmedCount()).mapToInt(Integer::intValue).sum();
	}

	@PostConstruct//generamos un resultado al arrancar la aplicacion
	@Scheduled(cron = "* * 1 * * *")//generamos un nuevo dato una vez al dia
	public void generarResultado() {

		Gson gson = new Gson();

		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest peticion = HttpRequest
				.newBuilder(URI.create(
						"https://raw.githubusercontent.com/BlankerL/DXY-COVID-19-Data/master/json/DXYArea.json"))
				.build();

		Resultado json = cliente.sendAsync(peticion, HttpResponse.BodyHandlers.ofString())// send devuelve una respuesta
				.thenApply(respuesta -> respuesta.body())
				.thenApply(body -> (Resultado) gson.fromJson(body, Resultado.class)).join();
		
		//ordenamos
		
		//NORMAL
		//Collections.sort(json.getResults(),new OrdenarPorCasos());
		//LAMBDA
		Collections.sort(json.getResults(),(Pais pais1,Pais pais2) -> Integer.compare(pais2.getConfirmedCount(),pais1.getConfirmedCount()));
		//LAMBDA con referencia a metodos
		//Collections.sort(json.getResults(),Integer::compare);
		
		
		
		System.out.println("Nuevo resultado Generado" + json);
		
		//sustituimos el ultimo valor generado.
		/*PReferimos guardar la peticion en una variable durante un dia entero,
		 * en vez de generar una peticion HTTP al recurso chino por cada vez que ponen /ultimoResultado
		 * para evitar la sobrecarga del servidor*/
		ultimoResultado = json;
	}

}
