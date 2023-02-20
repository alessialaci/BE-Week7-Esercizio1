package it.gestioneprenotazioni.config;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.gestioneprenotazioni.entities.Role;
import it.gestioneprenotazioni.entities.Utente;
import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {

	private String username;
	private String password;
	private Set<Role> roles;
	
	private boolean accountNonLocked = true;
	private boolean accountNonExpired = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	
	private List<GrantedAuthority> authorities;
	
	public UserDetailsImpl(String username, String password,  Set<Role> roles, List<GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build(Utente user) {
		List<GrantedAuthority> grants = user.getRoles().stream()
							.map(role -> new SimpleGrantedAuthority(role.getType().name()))
							.collect(Collectors.toList());
		
		return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getRoles(), grants);
	}
	
	
}
