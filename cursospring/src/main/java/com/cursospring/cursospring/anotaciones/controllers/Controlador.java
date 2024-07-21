package com.cursospring.cursospring.anotaciones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controlador {
	
	@RequestMapping("/muestraFormulario")
	public String muestraPagina() {
		return "paginaEjemplo";
	}
}
