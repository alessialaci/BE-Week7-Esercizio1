package it.gestioneprenotazioni.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.gestioneprenotazioni.entities.Edificio;
import it.gestioneprenotazioni.entities.Postazione;
import it.gestioneprenotazioni.entities.Prenotazione;
import it.gestioneprenotazioni.entities.Role;
import it.gestioneprenotazioni.entities.Utente;

@Configuration
public class GestioneConfig {

	@Bean
	public Edificio edificio() {
		Edificio e = new Edificio();
		return e;
	}
	
	@Bean
	public Postazione postazione() {
		Postazione p = new Postazione();
		return p;
	}
	
	@Bean
	public Prenotazione prenotazione() {
		Prenotazione pr = new Prenotazione();
		return pr;
	}
	
	@Bean
	public Utente utente() {
		Utente u = new Utente();
		return u;
	}
	
	@Bean
	public Role r() {
		Role r = new Role();
		return r;
	}
	
}
