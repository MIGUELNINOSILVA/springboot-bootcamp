package com.cursospring.cursospring.anotaciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.cursospring.cursospring.anotaciones")
public class EmpleadosConfig {

	// Definir el Bean para InformeFinanciero Departamento Compras
	@Bean
	public CreacionInformeFinanciero informeFinancieroDtoCompras() { // Id del bean inyectado

		return new InformeFinancieroDtoCompras();
	}

	// Definir Bean para DirectorFinanicero e inyectar dependencias
	@Bean
	public Empleados directorFinanciero() {
		return new DirectorInformeFinanciero(this.informeFinancieroDtoCompras());
	}
}
