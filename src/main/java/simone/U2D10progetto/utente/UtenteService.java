package simone.U2D10progetto.utente;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import simone.U2D10progetto.exception.BadRequestException;
import simone.U2D10progetto.exception.NotFoundException;
import simone.U2D10progetto.utente.payload.UtenteRegistrationPayload;

@Service
public class UtenteService {
	@Autowired
	private UtenteRepository usersRepo;

	public Utente create(UtenteRegistrationPayload u) {
		usersRepo.findByEmail(u.getEmail()).ifPresent(user -> {
			throw new BadRequestException("Email " + user.getEmail() + " already in use!");
		});
		Utente newUser = new Utente(u.getUsername(), u.getName(), u.getSurname(), u.getEmail(), u.getPassword());
		return usersRepo.save(newUser);
	}

	public Page<Utente> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return usersRepo.findAll(pageable);
	}

	public Utente findById(UUID id) throws NotFoundException {
		return usersRepo.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato"));
	}

	public Utente findByEmail(String email) throws NotFoundException {
		return usersRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente non trovato"));
	}

	public Utente findByIdAndUpdate(UUID id, UtenteRegistrationPayload u) throws NotFoundException {
		Utente found = this.findById(id);

		found.setId(id);
		found.setUsername(u.getUsername());
		found.setName(u.getName());
		found.setSurname(u.getSurname());
		found.setEmail(u.getEmail());

		return usersRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Utente found = this.findById(id);
		usersRepo.delete(found);
	}

}