package it.gestioneprenotazioni.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.gestioneprenotazioni.entities.Edificio;
import it.gestioneprenotazioni.services.EdificioService;

@RestController
@RequestMapping("/")
public class EdificioController {

	@Autowired
	private EdificioService es;
	
	@PostMapping("/login_success")
	@ResponseBody
	public String login_success() {
		return "login_success";
	}
	
	@GetMapping("edifici")
	public ResponseEntity<List<Edificio>> getAllEdific() {
		List<Edificio> edifici = es.getAll();
		
		if(edifici.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(edifici, HttpStatus.OK);
	}
	
	@GetMapping("edifici/{id}")
	public ResponseEntity<Object> getById(@PathVariable Integer id) {
		
		Optional<Edificio> edificioObj = es.getById(id);
		
		ResponseEntity<Object> check = checkExist(edificioObj);
		if(check != null) return check;
		
		return new ResponseEntity<>(edificioObj.get(), HttpStatus.OK);
	}
	
	@GetMapping("edifici_page")
	public ResponseEntity<Object> getAll_page(Pageable pageable) {
		Page<Edificio> edifici = es.getAll_page(pageable);
		
		if(edifici.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(edifici, HttpStatus.OK);
	}
	
	@PostMapping("edifici")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> createEdificio(@RequestBody Edificio p) {
		Edificio person = es.save(p);
		
		return new ResponseEntity<>(person, HttpStatus.CREATED);
	}
	
	@PutMapping("edifici/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Edificio _edificio) {
		
		Optional<Edificio> edificioObj = es.getById(id);
		
		ResponseEntity<Object> check = checkExist(edificioObj);
		if(check != null) return check;
		
		Edificio edificio = edificioObj.get();
		edificio.setCitta( _edificio.getCitta() );
		es.save(edificio);
		
		return new ResponseEntity<Object>(edificio, HttpStatus.CREATED);
	}
	
	@DeleteMapping("edifici/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deletePerson(@PathVariable int id) {
		Optional<Edificio> edificioObj = es.getById(id);
		
		ResponseEntity<Object> check = checkExist(edificioObj);
		if(check != null) {
			return check;
		}
		
		es.delete(edificioObj.get());
		
		return new ResponseEntity<>(String.format("Edificio con id %d cancellato", id), HttpStatus.OK);
	}
	
	private ResponseEntity<Object> checkExist(Optional<Edificio> obj) {
		if(!obj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return null;
	}
	
}
