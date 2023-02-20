package it.gestioneprenotazioni.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.gestioneprenotazioni.entities.Edificio;
import it.gestioneprenotazioni.repositories.EdificioRepository;

@Service
public class EdificioService {
	
	@Autowired
	private EdificioRepository edificioRepo;
	
	public void insert(Edificio e) {
		try {
			edificioRepo.save(e);
			System.out.println("Edificio inserito con successo");
		} catch (Exception ex) {
			System.out.println("Non è stato possibile salvare l'edificio");
		}
	}
	
    public Edificio save(Edificio e) {
    	return edificioRepo.save(e);
    }
    
    public Optional<Edificio> getById(int id) {
		return edificioRepo.findById(id);
	}
    
    public List<Edificio> getAll() {
    	return edificioRepo.findAll();
    }
    
    public Page<Edificio> getAll_page(Pageable pageable) {
		return edificioRepo.findAll(pageable);
	}
    
    public void delete(Edificio e) {
		edificioRepo.delete(e);
	}
    
}
