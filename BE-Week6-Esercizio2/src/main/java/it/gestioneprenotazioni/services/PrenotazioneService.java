package it.gestioneprenotazioni.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.gestioneprenotazioni.entities.Prenotazione;
import it.gestioneprenotazioni.repositories.PrenotazioneRepository;

@Service
public class PrenotazioneService {

	@Autowired
	private PrenotazioneRepository prenotazioneRepo;
	
	public void insert(Prenotazione pren) {
		try {
			prenotazioneRepo.save(pren);
			System.out.println("Prenotazione inserita con successo");
		} catch (Exception ex) {
			System.out.println("Non è stato possibile salvare la Prenotazione");
		}
	}
	
    public long getCountPrenotazioni(LocalDate data, int id) {
    	return prenotazioneRepo.getCountPrenotazioni(data, id);
    }
    
    public long getCountPrenotazioniUtente(LocalDate data, int id) {
    	return prenotazioneRepo.getCountPrenotazioniUtente(data, id);
    }
    
    public Optional<Prenotazione> getById(int id) {
		return prenotazioneRepo.findById(id);
	}
    
    public Prenotazione save(Prenotazione p) {
    	return prenotazioneRepo.save(p);
    }
    
    public List<Prenotazione> getAll() {
    	return prenotazioneRepo.findAll();
    }
    
    public Page<Prenotazione> getAll_page(Pageable pageable) {
		return prenotazioneRepo.findAll(pageable);
	}
    
    public void delete(Prenotazione p) {
		prenotazioneRepo.delete(p);
	}
	
}
