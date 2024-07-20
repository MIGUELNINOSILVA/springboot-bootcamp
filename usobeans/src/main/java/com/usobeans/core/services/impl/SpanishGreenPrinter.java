package com.usobeans.core.services.impl;

import org.springframework.stereotype.Component;

import com.usobeans.core.services.GreenPrinter;

@Component
public class SpanishGreenPrinter implements GreenPrinter{

	@Override
	public String print() {
		return "verde";
	}

}
