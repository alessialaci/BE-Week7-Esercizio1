package it.gestioneprenotazioni.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.gestioneprenotazioni.entities.Utente;
import it.gestioneprenotazioni.services.UtenteService;

@RestController
@RequestMapping("/")
public class UtenteController {

	@Autowired
	private UtenteService us;
	
	@GetMapping("utenti")
	public ResponseEntity<List<Utente>> getAll() {
		List<Utente> utenti = us.getAll();
		
		if(utenti.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(utenti, HttpStatus.OK);
	}
	
	@GetMapping("utenti/{id}")
	public ResponseEntity<Object> getById(@PathVariable Integer id) {
		
		Optional<Utente> utenteObj = us.getById(id);
		
		ResponseEntity<Object> check = checkExists(utenteObj);
		if(check != null) return check;
		
		return new ResponseEntity<>(utenteObj.get(), HttpStatus.OK);
	}
	
	@GetMapping("utenti_page")
	public ResponseEntity<Object> getAll_page(Pageable pageable) {
		Page<Utente> utenti = us.getAll_page(pageable);
		
		if(utenti.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(utenti, HttpStatus.OK);
	}
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@GetMapping("/auth_update_utente_pw")
	public String auth_update_utente_pw() {
		int id = 2;
		
		Utente u = us.getById(id).get();
		String pw = u.getPassword();
		u.setPassword( pwEncoder.encode(pw) );
		us.save(u);
		
		return "utente aggiornato";
	}
	
	@PostMapping("utenti")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> postEdificio(@RequestBody Utente u) {
		Utente utenti = us.save(u);
		return new ResponseEntity<>(utenti, HttpStatus.CREATED);
	}
	
	@PutMapping("utenti/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Utente _utente) {
		
		Optional<Utente> utenteObj = us.getById(id);
		
		ResponseEntity<Object> check = checkExists(utenteObj);
		if(check != null) return check;
		
		Utente utente = utenteObj.get();
		utente.setNome( _utente.getNome() );
		us.save(utente);
		
		
		return new ResponseEntity<Object>(utente, HttpStatus.CREATED);
	}
	
	@DeleteMapping("utenti/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		
		Optional<Utente> utenteObj = us.getById(id);
		ResponseEntity<Object> check = checkExists(utenteObj);
		if(check != null) return check;
		
		us.delete(utenteObj.get());
		return new ResponseEntity<>(
			String.format("User with id %d deleted!", id), HttpStatus.OK	
		);
	}
	
	private ResponseEntity<Object> checkExists(Optional<Utente> obj) {
		if( !obj.isPresent() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return null;
	}
	
}
