package com.dawes.modelo;

import java.util.List;

public class Resultado {
	
	private List<Pais> results;
	private String success;
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public List<Pais> getResults() {
		return results;
	}
	public void setResults(List<Pais> results) {
		this.results = results;
	}

	
	
}
