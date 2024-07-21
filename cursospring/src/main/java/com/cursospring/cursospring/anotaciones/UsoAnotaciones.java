package com.cursospring.cursospring.anotaciones;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsoAnotaciones {
	public static void main(String[] args) {
		/*
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext(
				"applicationContextAnotations.xml");*/

		//leer archivo de configuración
		AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(EmpleadosConfig.class);
		
		// Pedir un bean
		Empleados Miguel = contexto.getBean("ComercialExperimentado", Empleados.class);
		Empleados Lucia = contexto.getBean("ComercialExperimentado", Empleados.class);

		System.out.println(Miguel);
		System.out.println(Lucia);
		contexto.close();
	}
}
