package it.gestioneprenotazioni.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	// Inserendo allowedOrigins si permette di eseguire i metodi scritti soltanto dalla porta specifica (5500)
	// Altrimenti di default è possibile farle da tutte le porte

	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/edifici")
	    		.allowedOrigins("http://127.0.0.1:5500")
	    		.allowedMethods("GET", "POST", "PUT", "DELETE");
	}
	
}
