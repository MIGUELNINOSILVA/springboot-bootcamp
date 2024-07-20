package com.usobeans.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.usobeans.core.services.BluePrinter;
import com.usobeans.core.services.ColourPrinter;
import com.usobeans.core.services.GreenPrinter;
import com.usobeans.core.services.RedPrinter;
import com.usobeans.core.services.impl.ColourPrinterImpl;
import com.usobeans.core.services.impl.EnglishBluePrinter;
import com.usobeans.core.services.impl.EnglishGreenPrinter;
import com.usobeans.core.services.impl.EnglishRedPrinter;
import com.usobeans.core.services.impl.SpanishBluePrinter;

@Configuration
public class PrinterConfig {

	@Bean
	public BluePrinter bluePrinter() {
		return new SpanishBluePrinter();
	}
	
	@Bean
	public GreenPrinter greenPrinter() {
		return new EnglishGreenPrinter();
	}
	
	@Bean
	public RedPrinter redPrinter() {
		return new EnglishRedPrinter();
	}
	
	@Bean
	public ColourPrinter colourPrinter(RedPrinter redPrinter, BluePrinter bluePrinter,  GreenPrinter greenPrinter) {
		return new ColourPrinterImpl(redPrinter, bluePrinter, greenPrinter);
	}
	
}
