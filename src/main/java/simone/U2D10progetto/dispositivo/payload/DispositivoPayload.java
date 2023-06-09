package simone.U2D10progetto.dispositivo.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import simone.U2D10progetto.utente.Utente;
import simone.U2D10progetto.util.Stato;
import simone.U2D10progetto.util.Tipo;

@AllArgsConstructor
@Data
public class DispositivoPayload {

	@NotNull(message = "Il modello è obbligatorio")
	String modello;
	@NotNull(message = "Non hai inserito un Tipo valido")
	Tipo tipo;
	@NotNull(message = "Non hai inserito unoStato valido")
	Stato stato;
	Utente utente;

	public DispositivoPayload(@NotNull(message = "Il modello è obbligatorio") String modello,
			@NotNull(message = "Non hai inserito un Tipo valido") Tipo tipo,
			@NotNull(message = "Non hai inserito unoStato valido") Stato stato) {
		super();
		this.modello = modello;
		this.tipo = tipo;
		this.stato = stato;
		this.utente = null;
	}

}
