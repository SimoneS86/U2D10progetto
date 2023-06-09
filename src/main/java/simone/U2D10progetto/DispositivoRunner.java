package simone.U2D10progetto;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import simone.U2D10progetto.dispositivo.DispositivoService;
import simone.U2D10progetto.dispositivo.payload.DispositivoPayload;
import simone.U2D10progetto.util.Stato;
import simone.U2D10progetto.util.Tipo;

@Component
public class DispositivoRunner implements CommandLineRunner {
	@Autowired
	DispositivoService dispositivoService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		for (int i = 0; i < 10; i++) {
			try {
				String modello = faker.commerce().productName();
				Tipo tipo = faker.options().option(Tipo.class);
				Stato stato = faker.options().option(Stato.class);

				DispositivoPayload disp = new DispositivoPayload(modello, tipo, stato);
				// dispositivoService.create(disp);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}