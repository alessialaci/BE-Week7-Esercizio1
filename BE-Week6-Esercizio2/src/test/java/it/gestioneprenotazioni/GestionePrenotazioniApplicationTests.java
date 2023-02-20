package it.gestioneprenotazioni;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GestionePrenotazioniApplicationTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate rt;
	
	@Test
	public void assertThatTest1MethodContainsWord() throws Exception {
		assertThat(this.rt.getForObject("http://localhost:" + port + "/informativo/it", String.class))
				.contains("prenotazione");
	}
	
}
