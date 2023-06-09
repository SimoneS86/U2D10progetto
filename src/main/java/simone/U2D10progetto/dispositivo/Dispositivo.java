package simone.U2D10progetto.dispositivo;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import simone.U2D10progetto.utente.Utente;
import simone.U2D10progetto.util.Stato;
import simone.U2D10progetto.util.Tipo;

@Entity
@Table(name = "dispositivi")
@Data
@NoArgsConstructor
public class Dispositivo {

	@Id
	@GeneratedValue
	private UUID id;
	private String modello;
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	@Enumerated(EnumType.STRING)
	private Stato stato;
	@ManyToOne
	private Utente utente;

	public Dispositivo(String modello, Tipo tipo, Stato stato) {
		this.modello = modello;
		this.tipo = tipo;
		this.stato = stato;
		this.utente = null;
	}

	// Getters and setters...
}
