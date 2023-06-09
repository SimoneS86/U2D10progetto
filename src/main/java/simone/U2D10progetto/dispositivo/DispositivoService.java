package simone.U2D10progetto.dispositivo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import simone.U2D10progetto.dispositivo.payload.DispositivoPayload;
import simone.U2D10progetto.exception.NotFoundException;

@Service
public class DispositivoService {
	@Autowired
	private DispositivoRepository dispositivoRepo;

	public Dispositivo create(DispositivoPayload d) {
		Dispositivo newDispositivo = new Dispositivo(d.getModello(), d.getTipo(), d.getStato());
		return dispositivoRepo.save(newDispositivo);
	}

	public Page<Dispositivo> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return dispositivoRepo.findAll(pageable);
	}

	public Dispositivo findById(UUID id) throws NotFoundException {
		return dispositivoRepo.findById(id).orElseThrow(() -> new NotFoundException("Dispositivo non trovato"));
	}

	public Dispositivo findByIdAndUpdate(UUID id, DispositivoPayload d) throws NotFoundException {
		Dispositivo found = this.findById(id);

		found.setId(id);
		found.setModello(d.getModello());
		found.setTipo(d.getTipo());
		found.setStato(d.getStato());
		found.setUtente(d.getUtente());

		return dispositivoRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Dispositivo found = this.findById(id);
		dispositivoRepo.delete(found);
	}

//	public void assegnaDispositivo(UUID utenteId, UUID dispositivoId) throws NotFoundException {
//		Utente utente = findById(utenteId);
//		List<Dispositivo> dispositivi = utente.getDispositivi();
//		dispositivi.add(dispositivo);
//		utente.setDispositivi(dispositivi);
//		usersRepo.save(utente);
//	}

//	public void rimuoviDispositivo(UUID utenteId, Dispositivo dispositivo) throws NotFoundException {
//		Utente utente = findById(utenteId);
//		List<Dispositivo> dispositivi = utente.getDispositivi();
//		dispositivi.remove(dispositivo);
//		utente.setDispositivi(dispositivi);
//		usersRepo.save(utente);
//	}

}