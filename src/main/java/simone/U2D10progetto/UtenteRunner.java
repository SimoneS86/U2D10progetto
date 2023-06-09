package simone.U2D10progetto;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import simone.U2D10progetto.utente.UtenteService;
import simone.U2D10progetto.utente.payload.UtenteRegistrationPayload;

@Component
public class UtenteRunner implements CommandLineRunner {
	@Autowired
	UtenteService usersService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		for (int i = 0; i < 5; i++) {
			try {
				String username = faker.funnyName().name();
				String name = faker.name().firstName();
				String surname = faker.name().lastName();
				String email = faker.internet().emailAddress();
				String password = faker.internet().password();

				UtenteRegistrationPayload user = new UtenteRegistrationPayload(username, name, surname, email,
						password);
				// usersService.create(user);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}