package simone.U2D10progetto.utente;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import simone.U2D10progetto.dispositivo.Dispositivo;

@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
public class Utente implements UserDetails {
	public enum Role {
		ADMIN, USER
	}

	@Id
	@GeneratedValue
	private UUID id;
	private String username;
	private String name;
	private String surname;
	private String email;
	private String password;
	private Role role;
	@OneToMany(mappedBy = "utente")
	private List<Dispositivo> dispositivi;

	public Utente(String username, String name, String surname, String email, String password) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.role = Role.USER;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	// Altre implementazioni e metodi della classe Utente...

	// Esempio di metodo per assegnare un dispositivo all'utente
	public void assegnaDispositivo(Dispositivo dispositivo) {
		dispositivi.add(dispositivo);
	}

	// Esempio di metodo per rimuovere un dispositivo dall'utente
	public void rimuoviDispositivo(Dispositivo dispositivo) {
		dispositivi.remove(dispositivo);
	}
}
