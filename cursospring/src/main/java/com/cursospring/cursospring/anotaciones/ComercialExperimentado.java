package com.cursospring.cursospring.anotaciones;

import org.springframework.stereotype.Component;

@Component("ComercialExperimentado")
public class ComercialExperimentado implements Empleados {

	@Override
	public String getTareas() {
		return "Vender y vender más";
	}

	@Override
	public String getInformes() {
		return "Informe generado por el comercial";
	}

}
