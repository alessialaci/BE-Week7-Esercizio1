package it.gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.gestioneprenotazioni.entities.Role;

@Repository
public interface RuoloRepository extends JpaRepository<Role, Integer> {
	
}
