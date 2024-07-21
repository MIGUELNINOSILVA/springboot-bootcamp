package com.cursospring.cursospring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsoEmpleados {

	public static void main(String[] args) {
		/*
		 * //Creación de objetos de tipo empleados Empleados empleado1 = new
		 * DirectorEmpleado();
		 * 
		 * // Uso de los objetos creados
		 * 
		 * System.out.println(empleado1.getTareas());
		 */

		// Crear un contexto
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("applicationContext.xml");

		SecretarioEmpleado Juan = contexto.getBean("miEmpleado", SecretarioEmpleado.class);

		System.out.println(Juan.getTareas());
		System.out.println(Juan.getInforme());
		System.out.println(Juan.getEmail());

		contexto.close();

	}

}
