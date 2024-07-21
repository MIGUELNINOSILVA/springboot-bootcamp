package com.cursospring.cursospring.anotaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("ComercialExperimentado")
public class ComercialExperimentado implements Empleados {

	private CreacionInformeFinanciero nuevoInforme;
	
	@Autowired
	public ComercialExperimentado(@Qualifier("informeFinancieroTrim1") CreacionInformeFinanciero nuevoInforme) {
		this.nuevoInforme = nuevoInforme;
	}
	
	@Override
	public String getTareas() {
		return "Vender y vender más";
	}

	@Override
	public String getInformes() {
		return this.nuevoInforme.getInformeFinanciero();
	}

}
