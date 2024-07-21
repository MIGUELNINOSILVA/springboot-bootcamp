package com.cursospring.cursospring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsoDemoSingletonPrototype {

	public static void main(String[] args) {
		// Crear un contexto
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("applicationContext3.xml");
		
		//Petición de Beans al contenedor Spring
		DirectorEmpleado Maria = contexto.getBean("miEmpleado", DirectorEmpleado.class);
		
		System.out.println(Maria.getInforme());
		
		contexto.close();
	}
}
