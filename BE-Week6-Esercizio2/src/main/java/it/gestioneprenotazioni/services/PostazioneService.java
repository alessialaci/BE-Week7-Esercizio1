package it.gestioneprenotazioni.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.gestioneprenotazioni.entities.Postazione;
import it.gestioneprenotazioni.repositories.PostazioneRepository;

@Service
public class PostazioneService {
	
	@Autowired
	private PostazioneRepository postazioneRepo;
	
	public void insert(Postazione p) {
		try {
			postazioneRepo.save(p);
			System.out.println("Postazione inserita con successo");
		} catch (Exception ex) {
			System.out.println("Non è stato possibile salvare la Postazione");
		}
	}
	
	public Postazione save(Postazione e) {
    	return postazioneRepo.save(e);
    }
	
	public List<Postazione> getAll() {
    	return postazioneRepo.findAll();
    }
    
    public Page<Postazione> getAll_page(Pageable pageable) {
		return postazioneRepo.findAll(pageable);
	}
    
    public void delete(Postazione e) {
    	postazioneRepo.delete(e);
	}
	
    public Optional<Postazione> getById(int id) {
		return postazioneRepo.findById(id);
	}
	
    public List<Postazione> getFromTipoAndCitta(String tipo, String citta) {
    	return postazioneRepo.findPostazioneByTipoAndCittà(tipo, citta);
    }

}
