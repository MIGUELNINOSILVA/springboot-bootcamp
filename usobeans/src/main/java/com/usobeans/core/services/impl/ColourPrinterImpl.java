package com.usobeans.core.services.impl;

import org.springframework.stereotype.Component;

import com.usobeans.core.services.BluePrinter;
import com.usobeans.core.services.ColourPrinter;
import com.usobeans.core.services.GreenPrinter;
import com.usobeans.core.services.RedPrinter;

@Component
public class ColourPrinterImpl implements ColourPrinter{
	
	private RedPrinter redPrinter;
	private BluePrinter bluePrinter;
	private GreenPrinter greenPrinter;
	
	public ColourPrinterImpl(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
		this.redPrinter = redPrinter;
		this.bluePrinter = bluePrinter;
		this.greenPrinter = greenPrinter;
	}

	@Override
	public String print() {
		return String.join(" ,", redPrinter.print(), " ,", bluePrinter.print(), " ,", greenPrinter.print());
	}

}
