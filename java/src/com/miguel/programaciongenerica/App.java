package com.miguel.programaciongenerica;

import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		/*
		 * Empleado listaEmpleados[] = new Empleado[3];
		 * 
		 * listaEmpleados[0] = new Empleado("Ana", 45, 2500); listaEmpleados[1] = new
		 * Empleado("Antonio", 55, 2000); listaEmpleados[2] = new Empleado("María", 25,
		 * 2600);
		 */

		ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();

		listaEmpleados.add(new Empleado("Ana", 45, 2500));
		listaEmpleados.add(new Empleado("Antonio", 55, 2000));
		listaEmpleados.add(new Empleado("María", 25, 2600));
		listaEmpleados.add(new Empleado("Miguel", 20, 500));

		listaEmpleados.trimToSize();
		
		for (Empleado e : listaEmpleados) {
			System.out.println(e.dameDatos());
		}
	}
}
