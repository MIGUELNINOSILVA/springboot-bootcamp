package com.cursospring.cursospring;

public class DirectorEmpleado implements Empleados {

	//Creación de campo tipo CreacionInformes (interfaz)
	private CreacionInformes informeNuevo;
	
	//Creación de constructor que inyecta la dependencia
	public DirectorEmpleado(CreacionInformes informeNuevo) {
		this.informeNuevo = informeNuevo;
	}
	
	@Override
	public String getTareas() {
		return "Gestionar la plantilla de la empresa";
	}

	@Override
	public String getInforme() {
		return "Informe creado por el director: "+informeNuevo.getInforme();
	}

}
