package it.gestioneprenotazioni.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.gestioneprenotazioni.entities.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
	
	@Query(
			nativeQuery = true,
			value = "SELECT COUNT(*) FROM prenotazioni WHERE data_prenotazione = :data AND postazione_id = :postazioneId")
	long getCountPrenotazioni(@Param("data")LocalDate data, @Param("postazioneId")int postazioneId);
	
	@Query(
			nativeQuery = true,
			value = "SELECT COUNT(*) FROM prenotazioni WHERE data_prenotazione = :data AND utente_id = :utenteId")
	long getCountPrenotazioniUtente(@Param("data")LocalDate data, @Param("utenteId")int utenteId);
	
}
