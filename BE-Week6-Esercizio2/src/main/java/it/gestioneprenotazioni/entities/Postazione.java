package it.gestioneprenotazioni.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "postazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
public class Postazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "postazione_id")
	private int postazioneId;
	
	private String descrizione;
	
	@Enumerated(EnumType.STRING)
	private TipoPostazione tipo;
	
	@Column(name = "max_occupanti")
	private int maxOccupanti;
	
	@ManyToOne
	@JoinColumn(name = "edificio_id")
	@JsonIgnoreProperties("postazioni")
	private Edificio edificio;
	
	@OneToMany(mappedBy = "postazione")
	@JsonIgnoreProperties("postazione")
	private Set<Prenotazione> prenotazioni;
	
	@Override
	public String toString() {
		return "Postazione " + postazioneId + " - Nome edificio: " + edificio.getNome() +" - Città: " + edificio.getCitta() + " - Indirizzo: " + edificio.getIndirizzo();
	}

}
