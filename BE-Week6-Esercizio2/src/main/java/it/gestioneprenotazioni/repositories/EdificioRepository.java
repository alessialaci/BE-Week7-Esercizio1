package it.gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.gestioneprenotazioni.entities.Edificio;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Integer> {

}
