package com.cursospring.cursospring.anotaciones;

import org.springframework.stereotype.Component;

@Component
public class InformeFinancieroTrim2 implements CreacionInformeFinanciero {

	@Override
	public String getInformeFinanciero() {
		return "Presentación de informe financiero del trimestre 2";
	}

}
