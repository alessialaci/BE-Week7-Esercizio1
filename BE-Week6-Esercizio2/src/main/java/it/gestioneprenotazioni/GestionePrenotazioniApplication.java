package it.gestioneprenotazioni;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.gestioneprenotazioni.config.GestioneConfig;
import it.gestioneprenotazioni.entities.Edificio;
import it.gestioneprenotazioni.entities.Postazione;
import it.gestioneprenotazioni.entities.Prenotazione;
import it.gestioneprenotazioni.entities.Role;
import it.gestioneprenotazioni.entities.RoleType;
import it.gestioneprenotazioni.entities.TipoPostazione;
import it.gestioneprenotazioni.entities.Utente;
import it.gestioneprenotazioni.services.EdificioService;
import it.gestioneprenotazioni.services.PostazioneService;
import it.gestioneprenotazioni.services.PrenotazioneService;
import it.gestioneprenotazioni.services.RoleService;
import it.gestioneprenotazioni.services.UtenteService;

@SpringBootApplication
public class GestionePrenotazioniApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(GestionePrenotazioniApplication.class, args);
	}
	
	@Autowired
	EdificioService es;
	
	@Autowired
	PostazioneService ps;
	
	@Autowired
	UtenteService us;
	
	@Autowired
	PrenotazioneService prens;
	
	@Autowired
	RoleService rs;
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(GestioneConfig.class);

	static Scanner scan = new Scanner(System.in);
	
	@Override
	public void run(String... args) throws Exception {
		//gestioneApp();
		popolaDb();
		
        ((AnnotationConfigApplicationContext)ctx).close();
	}
	
	public void popolaDb() {
//		Role r1 = (Role) ctx.getBean("r");
//		r1.setType(RoleType.ROLE_ADMIN);
//		
//		Role r2 = (Role) ctx.getBean("r");
//		r2.setType(RoleType.ROLE_USER);
//		
//		rs.saveRole(r1);
//		rs.saveRole(r2);
//		
//		Utente u = (Utente) ctx.getBean("utente");
//		u.setUsername("mario");
//		u.setNome("Mario Rossi");
//		u.setEmail("m@r.it");
//		u.setPassword("mario");
//		u.setActive(true);
//		u.setRoles(new HashSet<>() {{
//			add(r1);
//		}});
//		
//		Utente u2 = (Utente) ctx.getBean("utente");
//		u2.setUsername("luigi");
//		u2.setNome("Luigi Verdi");
//		u2.setEmail("l@v.it");
//		u2.setPassword("luigi");
//		u2.setActive(true);
//		u2.setRoles(new HashSet<>() {{
//			add(r2);
//		}});
//		
//		us.insert(u);
//		us.insert(u2);
		
		Edificio e1 = (Edificio) ctx.getBean("edificio");
		e1.setNome("Edificio");
		e1.setCitta("Lecce");
		e1.setCodice("1234abcd");
		e1.setIndirizzo("Via Ciao");
		
		es.save(e1);
	}
	
	// METODI PER GESTIONE DELL'APP
	public void gestioneApp() {
		System.out.println("Cosa vuoi fare?");
		System.out.println("1 - Amministrazione");
		System.out.println("2 - Registra Utente");
		System.out.println("3 - Prenota postazione");
		System.out.println("4 - Ricerca Postazione per Città e Tipo");
		
		int selezione = scan.nextInt();
		
		switch(selezione) {
			case(1):
				amministrazione();
				break;
			case(2):
				creaUtente();
				break;
			case(3):
				creaPrenotazione();
				break;
			case(4):
				findPrenotazioneByTipoCitta();
				break;
			default:
				System.out.println("Numero non presente in elenco");
				break;
		}
	}
	
	public void amministrazione() {
		System.out.println("Cosa vuoi fare?");
		System.out.println("1 - Crea Edificio");
		System.out.println("2 - Crea Postazione");
		
		int scelta = scan.nextInt();
		
		switch(scelta) {
			case(1):
				creaEdificio();
				break;
			case(2):
				creaPostazione();
				break;
			default:
				System.out.println("Numero non presente in elenco");
				break;
		}
	}
	
	
	// EDIFICIO --> CREAZIONE + RECUPERO PER ID
	public void creaEdificio() {
		scan.nextLine();
		System.out.println("Inserisci il nome");
		String nome = scan.nextLine();
		System.out.println("Inserisci l'indirizzo");
		String indirizzo = scan.nextLine();
		System.out.println("Inserisci la città");
		String citta = scan.nextLine();
		
		insertEdificio(nome, indirizzo, citta);
	}

	public Edificio insertEdificio(String nome, String indirizzo, String citta) {
		Edificio e = (Edificio) ctx.getBean("edificio");
		e.setNome(nome);
		e.setIndirizzo(indirizzo);
		e.setCitta(citta);
		
		es.insert(e);
		
		return e;
	}
	
	public Edificio getEdificioById(int id) {
		try {
			Optional<Edificio> edificioOpt = es.getById(id);
			Edificio edificio = edificioOpt.get();
			return edificio;
		} catch(Exception ex) {
			System.out.println("Edificio con #" + id + " non trovato");
			return null;
		}
	}
	
	
	// POSTAZIONE --> CREAZIONE + RECUPERO PER ID
	TipoPostazione tipoPost;
	public void creaPostazione() {
		scan.nextLine();
		System.out.println("Inserisci una descrizione per la postazione");
		String descrizione = scan.nextLine();
		System.out.println("Inserisci il tipo di postazione");
		System.out.println("1 - Privato");
		System.out.println("2 - Openspace");
		System.out.println("3 - Sala Riunioni");
		int tipo = scan.nextInt();
		
		if(tipo == 1) {
			tipoPost = TipoPostazione.PRIVATO;
		} else if(tipo == 2) {
			tipoPost = TipoPostazione.OPENSPACE;
		} else if(tipo == 3) {
			tipoPost = TipoPostazione.SALA_RIUNIONI;
		} else {
			System.out.println("Il numero inserito non è corretto");
			return;
		}
		
		System.out.println("Inserisci il numero massimo di partecipanti");
		int maxPartecipanti = scan.nextInt();
		System.out.println("Inserisci l'id dell'Edificio in cui si trova la postazione");
		int idEdificio = scan.nextInt();
		
		Edificio edificio = getEdificioById(idEdificio);
		
		insertPostazione(descrizione, tipoPost, maxPartecipanti, edificio);
	}

	public Postazione insertPostazione(String descrizione, TipoPostazione tipo, int maxOccupanti, Edificio edificio) {
		Postazione p = (Postazione) ctx.getBean("postazione");
		p.setDescrizione(descrizione);
		p.setTipo(tipo);
		p.setMaxOccupanti(maxOccupanti);
		p.setEdificio(edificio);
		
		ps.insert(p);
		
		return p;
	}
	
	public Postazione getPostazioneById(int id) {
		try {
			Optional<Postazione> postazioneOpt = ps.getById(id);
			Postazione postazione = postazioneOpt.get();
			return postazione;
		} catch(Exception ex) {
			System.out.println("Postazione con #" + id + " non trovato");
			return null;
		}
	}
	
	
	// UTENTE --> CREAZIONE + RECUPERO PER ID
	public void creaUtente() {
		scan.nextLine();
		System.out.println("Inserisci il tuo nome completo");
		String nome = scan.nextLine();
		System.out.println("Inserisci uno username");
		String username = scan.nextLine();
		System.out.println("Inserisci un indirizzo email");
		String email = scan.nextLine();
		
		insertUtente(username, nome, email);
	}
	
	public Utente insertUtente(String username, String nome, String email) {
		Utente u = (Utente) ctx.getBean("utente");
		u.setUsername(username);
		u.setNome(nome);
		u.setEmail(email);
		
		us.insert(u);
		
		return u;
	}
	
	public Utente getUtenteById(int id) {
		try {
			Optional<Utente> utenteOpt = us.getById(id);
			Utente utente = utenteOpt.get();
			return utente;
		} catch(Exception ex) {
			System.out.println("Utente con #" + id + " non trovato");
			return null;
		}
	}
	
	
	// PRENOTAZIONE --> CREAZIONE
	public void creaPrenotazione() {
		System.out.println("Inserisci il tuo numero utente (id)");
		int idUtente = scan.nextInt();
		
		Utente utente = getUtenteById(idUtente);
		
		System.out.println("Inserisci il numero id della postazione da prenotare");
		int idPost = scan.nextInt();
		
		Postazione postazione = getPostazioneById(idPost);
		
		scan.nextLine();
		System.out.println("Inserisci la data di prenotazione della postazione nel formato AAAA-MM-GG");
		String dataPren = scan.nextLine();
		
		LocalDate dataPrenotazione = LocalDate.parse(dataPren);
		
		LocalDate dataCheck = dataPrenotazione.minusDays(2);
		
		if(LocalDate.now().isAfter(dataCheck)) {
			System.out.println("Mi dispiace, non puoi prenotare");
			System.exit(0);
		} else if(LocalDate.now().isEqual(dataCheck) || LocalDate.now().isBefore(dataCheck)) {
			long countElementi = prens.getCountPrenotazioni(dataPrenotazione, idPost);
			
			int nMaxOccupanti = postazione.getMaxOccupanti();
			
			if(countElementi == nMaxOccupanti) {
				System.out.println("La postazione " + idPost + " è completa per la data scelta! Non puoi prenotare");
				System.exit(0);
			} else if(countElementi < nMaxOccupanti){
				long contaElementi = prens.getCountPrenotazioniUtente(dataPrenotazione, idUtente);
				
				if(contaElementi >= 1) {
					System.out.println("Hai già una prenotazione per questa data. Non puoi prenotare altre postazioni");
					System.exit(0);
				} else {
					insertPrenotazione(dataPrenotazione, utente, postazione);
				}
			}
		}
	}
	
	public Prenotazione insertPrenotazione(LocalDate dataPrenotazione, Utente utente, Postazione postazione) {
		Prenotazione p = (Prenotazione) ctx.getBean("prenotazione");
		p.setDataPrenotazione(dataPrenotazione);
		p.setDataScadenza(dataPrenotazione.plusDays(1));
		p.setUtente(utente);
		p.setPostazione(postazione);
		
		prens.insert(p);
		
		return p;
	}
	
	
	// RICERCA
	String tipoPostazione;
	public void findPrenotazioneByTipoCitta() {
		System.out.println("Scegli il tipo della postazione");
		System.out.println("1 - Privato");
		System.out.println("2 - Openspace");
		System.out.println("3 - Sala Riunioni");
		int selezioneTipo = scan.nextInt();
		
		if (selezioneTipo == 1) {
			tipoPostazione = "PRIVATO";
		} else if (selezioneTipo == 2) {
			tipoPostazione = "OPENSPACE";
		} else if (selezioneTipo == 3) {
			tipoPostazione= "SALA_RIUNIONI";
		} else {
			System.out.println("Valore non corretto");
		}	
		scan.nextLine();
		
		System.out.println("Inserisci la città:");
		String citta = scan.nextLine();
		
		findFromTipoAndCitta(tipoPostazione, citta);
	}
	
	public void findFromTipoAndCitta(String tipo, String citta) {
		List<Postazione> postazione = ps.getFromTipoAndCitta(tipo, citta);
		postazione.stream().forEach(pos -> {
			System.out.println(pos);
		});
	}

}
