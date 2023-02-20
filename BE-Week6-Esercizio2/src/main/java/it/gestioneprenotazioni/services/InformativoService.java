package it.gestioneprenotazioni.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class InformativoService {
	
	@Value("${informativo.inglese}")
	private String inglese;
	
	@Value("${informativo.italiano}")
	private String italiano;

	public String informativo(String lingua) {
		if(lingua.equalsIgnoreCase("EN")) {
			return inglese;
		} else if(lingua.equalsIgnoreCase("IT")) {
			return italiano;
		} else {
			return "La lingua inserita non è supportata";
		}
	}
	
}
