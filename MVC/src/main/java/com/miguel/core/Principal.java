package com.miguel.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.miguel.core.config.Config;

public class Principal {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		System.out.println("Desde el contexto");
		ctx.close();
	}

}
