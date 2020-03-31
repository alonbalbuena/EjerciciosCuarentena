package com.dawes.servicios;

import java.util.Comparator;

import com.dawes.modelo.Pais;

public class OrdenarPorCasos implements Comparator<Pais> {

	@Override
	public int compare(Pais o1, Pais o2) {
		return Integer.compare(o2.getConfirmedCount(),o1.getConfirmedCount());
	}

}
