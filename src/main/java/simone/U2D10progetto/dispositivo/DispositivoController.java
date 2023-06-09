package simone.U2D10progetto.dispositivo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import simone.U2D10progetto.dispositivo.payload.DispositivoPayload;
import simone.U2D10progetto.exception.NotFoundException;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
	@Autowired
	private DispositivoService dispositivoService;

	@GetMapping("")
	public Page<Dispositivo> getDispositivi(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return dispositivoService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo saveDispositivo(@RequestBody @Validated DispositivoPayload body) {
		return dispositivoService.create(body);
	}

	@GetMapping("/{dispositivoId}")
	public Dispositivo getDispositivo(@PathVariable UUID dispositivoId) throws Exception {
		return dispositivoService.findById(dispositivoId);
	}

	@PutMapping("/{dispositivoId}")
	public Dispositivo updateDispositivo(@PathVariable UUID dispositivoId, @RequestBody DispositivoPayload body)
			throws Exception {
		return dispositivoService.findByIdAndUpdate(dispositivoId, body);
	}

	@DeleteMapping("/{dispositivoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDispositvo(@PathVariable UUID dispositivoId) throws NotFoundException {
		dispositivoService.findByIdAndDelete(dispositivoId);
	}

}