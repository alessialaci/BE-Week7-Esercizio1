package it.gestioneprenotazioni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.gestioneprenotazioni.entities.Role;
import it.gestioneprenotazioni.repositories.RuoloRepository;

@Service
public class RoleService {

	@Autowired
	private RuoloRepository roler;
	
	public Role saveRole(Role r) {
		return roler.save(r);
	}
	
}
