package it.gestioneprenotazioni.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prenotazione_id")
	private int prenotazioneId;
	
	@Column(name = "data_prenotazione")
	private LocalDate dataPrenotazione;
	
	@Column(name = "data_scadenza")
	private LocalDate dataScadenza;
	
	@ManyToOne
	@JoinColumn(name = "utente_id")
	@JsonIgnoreProperties("prenotazioni")
	private Utente utente;
	
	@ManyToOne
	@JoinColumn(name = "postazione_id")
	private Postazione postazione;

}
