package com.usobeans.core.services.impl;

import org.springframework.stereotype.Component;

import com.usobeans.core.services.RedPrinter;

@Component
public class SpanishRedPrinter implements RedPrinter{

	@Override
	public String print() {
		return "rojo";
	}

}
