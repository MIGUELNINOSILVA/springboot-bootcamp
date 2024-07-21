package com.cursospring.cursospring;

public class DirectorEmpleado implements Empleados {

	//Creación de campo tipo CreacionInformes (interfaz)
	private CreacionInformes informeNuevo;
	
	//Creación de constructor que inyecta la dependencia
	public DirectorEmpleado(CreacionInformes informeNuevo) {
		this.informeNuevo = informeNuevo;
	}
	
	// Metodo init. Ejecutar tareas antes de que el bean esté disponible
	
	public void init() {
		System.out.println("Tarea antes de que el bean esté disponible");
	}
	
	// Método destroy. Ejecutar tareas después de que el bean esté en uso
	public void destroy() {
		System.out.println("Tarea después de que el bean esté en uso");
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
