package com.cursospring.cursospring.anotaciones;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsoAnotaciones2 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext(
				"applicationContextAnotations.xml");

		// Pedir un bean
		Empleados Miguel = contexto.getBean("ComercialExperimentado", Empleados.class);

		System.out.println(Miguel.getInformes());
		contexto.close();
	}
}
