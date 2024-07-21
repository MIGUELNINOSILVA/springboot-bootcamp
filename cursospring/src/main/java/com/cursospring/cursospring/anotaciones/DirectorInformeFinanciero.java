package com.cursospring.cursospring.anotaciones;

public class DirectorInformeFinanciero implements Empleados {

	private CreacionInformeFinanciero informeFinanciero;
	
	public DirectorInformeFinanciero(CreacionInformeFinanciero informeFinanciero) {
		this.informeFinanciero = informeFinanciero;
	}

	@Override
	public String getTareas() {
		// TODO Auto-generated method stub
		return "Gestión y dirección de las operaciones financieras de la empresa";
	}

	@Override
	public String getInformes() {
		// TODO Auto-generated method stub
		return this.informeFinanciero.getInformeFinanciero();
	}	

}
