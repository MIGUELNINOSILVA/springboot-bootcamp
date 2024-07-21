package com.cursospring.cursospring;

public class JefeEmpleado implements Empleados{

	//Inyección de dependencias con un setter
	private CreacionInformes informeNuevo;
	
	public void setInformeNuevo(CreacionInformes informeNuevo) {
        this.informeNuevo = informeNuevo;
    }
	
	@Override
	public String getTareas() {
		return "Gestiono las cuestiones relativas a mis empleados de sección";
	}

	@Override
	public String getInforme() {
		return "Informe del Jefe Empleado: "+informeNuevo.getInforme();
	}
	
}
