package com.usobeans.core;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.usobeans.core.services.ColourPrinter;

import java.util.logging.Logger;

@SpringBootApplication
public class UsobeansApplication implements CommandLineRunner{

	Logger log = Logger.getLogger("Log");
	
	private ColourPrinter colourPrinter;
	
	public UsobeansApplication(ColourPrinter colourPrinter) {
		this.colourPrinter = colourPrinter;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UsobeansApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(colourPrinter.print());
	}

}
