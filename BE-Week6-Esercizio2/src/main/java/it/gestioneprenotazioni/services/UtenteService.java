package it.gestioneprenotazioni.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.gestioneprenotazioni.entities.Utente;
import it.gestioneprenotazioni.repositories.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepo;
	
	public void insert(Utente u) {
		try {
			utenteRepo.save(u);
			System.out.println("Utente inserito con successo");
		} catch (Exception ex) {
			System.out.println("Non è stato possibile salvare l'Utente");
		}
	}
	
    public Optional<Utente> getById(int id) {
		return utenteRepo.findById(id);
	}
    
    public Optional<Utente> getByUsername(String username) {
		return utenteRepo.findByUsername(username);
	}
    
    public Utente save(Utente u) {
    	return utenteRepo.save(u);
    }
    
    public List<Utente> getAll() {
    	return utenteRepo.findAll();
    }
    
    public Page<Utente> getAll_page(Pageable pageable) {
		return utenteRepo.findAll(pageable);
	}
    
    public void delete(Utente u) {
		utenteRepo.delete(u);
	}

}
