package it.gestioneprenotazioni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.gestioneprenotazioni.services.InformativoService;

@RestController
public class GestionePrenotazioniController {
	
	@Autowired
	InformativoService is;
	
	@GetMapping("/informativo/{lingua}")
	public String informativo(@PathVariable String lingua) {
		return is.informativo(lingua);
	}
	
}
