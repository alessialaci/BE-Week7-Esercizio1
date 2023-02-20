package it.gestioneprenotazioni.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.gestioneprenotazioni.entities.Prenotazione;
import it.gestioneprenotazioni.services.PrenotazioneService;

@RestController
@RequestMapping("/")
public class PrenotazioneController {

	@Autowired
	private PrenotazioneService ps;
	
	
	@GetMapping("prenotazioni")
	public ResponseEntity<List<Prenotazione>> getAll() {
		List<Prenotazione> prenotazioni = ps.getAll();
		
		if(prenotazioni.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(prenotazioni, HttpStatus.OK);
	}
	
	@GetMapping("prenotazioni/{id}")
	public ResponseEntity<Object> getById(@PathVariable Integer id) {
		
		Optional<Prenotazione> prenotazioneObj = ps.getById(id);
		
		ResponseEntity<Object> check = checkExists(prenotazioneObj);
		if(check != null) return check;
		
		return new ResponseEntity<>(prenotazioneObj.get(), HttpStatus.OK);
	}
	
	@GetMapping("prenotazioni_page")
	public ResponseEntity<Object> getAll_page(Pageable pageable) {
		Page<Prenotazione> prenotazioni = ps.getAll_page(pageable);
		
		if(prenotazioni.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(prenotazioni, HttpStatus.OK);
	}
	
	@PostMapping("prenotazioni")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> postEdificio(@RequestBody Prenotazione p) {
		Prenotazione prenotazione = ps.save(p);
		return new ResponseEntity<>(prenotazione, HttpStatus.CREATED);
	}
	
	@PutMapping("prenotazioni/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Prenotazione _prenotazione) {
		
		Optional<Prenotazione> prenotazioneObj = ps.getById(id);
		
		ResponseEntity<Object> check = checkExists(prenotazioneObj);
		if(check != null) return check;
		
		Prenotazione prenotazione = prenotazioneObj.get();
		prenotazione.setDataPrenotazione( _prenotazione.getDataPrenotazione() );
		ps.save(prenotazione);
		
		
		return new ResponseEntity<Object>(prenotazione, HttpStatus.CREATED);
	}
	
	@DeleteMapping("prenotazioni/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		
		Optional<Prenotazione> prenotazioneObj = ps.getById(id);
		ResponseEntity<Object> check = checkExists(prenotazioneObj);
		if(check != null) return check;
		
		ps.delete(prenotazioneObj.get());
		return new ResponseEntity<>(
			String.format("Booking with id %d deleted!", id), HttpStatus.OK	
		);
	}
	
	private ResponseEntity<Object> checkExists(Optional<Prenotazione> obj) {
		if( !obj.isPresent() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return null;
	}
	
}
