package com.miguel.core.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

	public static void main(String[] args) {
	
		String jdbcUrl = "jdbc:mysql://localhost:3306/gestionPedidosCRUD";
		
		String user = "root";
		
		String password = "3152108124";
		
		try {
			System.out.println("Intentado conectarme" + jdbcUrl);
			Connection miConexion = DriverManager.getConnection(jdbcUrl, user, password);
			
			System.out.println("Conexión exitosa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
