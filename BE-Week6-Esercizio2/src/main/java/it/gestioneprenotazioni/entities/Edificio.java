package it.gestioneprenotazioni.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.gestioneprenotazioni.config.StringAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "edifici")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
public class Edificio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "edificio_id")
	private int edificioId;
	
	private String nome;
	private String indirizzo;
	private String citta;
	
	@Convert(converter = StringAttributeConverter.class)
	private String codice;
	
	@OneToMany(mappedBy = "edificio")
	@JsonIgnoreProperties("edificio")
	private Set<Postazione> postazioni;	
	
}
