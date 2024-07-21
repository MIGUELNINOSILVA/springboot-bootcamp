package com.cursospring.cursospring.anotaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component("ComercialExperimentado")
public class ComercialExperimentado implements Empleados {

	private CreacionInformeFinanciero nuevoInforme;
	
	//Ejecución de código después de creación de Bean
	@PostConstruct
	public void init() {
		System.out.println("Ejecutando antes de la destrucción");
	}
	
	//Ejecución de código después de apagado la creación de Bean
	@PreDestroy
	public void destroy() {
		System.out.println("Ejecutando después de la destrucción");
	}
	
	/**
	 * Constructor que inyecta dependencias
	 * @param nuevoInforme
	 */
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
