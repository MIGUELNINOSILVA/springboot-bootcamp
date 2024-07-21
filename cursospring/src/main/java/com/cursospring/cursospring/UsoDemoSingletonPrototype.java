package com.cursospring.cursospring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsoDemoSingletonPrototype {

	public static void main(String[] args) {
		// Crear un contexto
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("applicationContext2.xml");
		
		//Petición de Beans al contenedor Spring
		SecretarioEmpleado Juan = contexto.getBean("miEmpleado", SecretarioEmpleado.class);
		SecretarioEmpleado Maria = contexto.getBean("miEmpleado", SecretarioEmpleado.class);
		
		System.out.println(Maria);
		System.out.println(Juan);
	}
}
