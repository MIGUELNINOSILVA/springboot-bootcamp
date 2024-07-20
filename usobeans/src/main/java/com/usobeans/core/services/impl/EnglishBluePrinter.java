package com.usobeans.core.services.impl;

import org.springframework.stereotype.Component;

import com.usobeans.core.services.BluePrinter;

@Component
public class EnglishBluePrinter implements BluePrinter{

	@Override
	public String print() {
		return "blue";
	}

}
