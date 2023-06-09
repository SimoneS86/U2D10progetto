package simone.U2D10progetto.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import simone.U2D10progetto.auth.payload.AuthenticationSuccessfullPayload;
import simone.U2D10progetto.exception.NotFoundException;
import simone.U2D10progetto.exception.UnauthorizedException;
import simone.U2D10progetto.utente.Utente;
import simone.U2D10progetto.utente.UtenteService;
import simone.U2D10progetto.utente.payload.UtenteLoginPayload;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UtenteService usersService;

//	@PostMapping("/register")
//	public ResponseEntity<Utente> register(@RequestBody @Validated UtenteRegistrationPayload body) {
//		Utente createdUser = usersService.create(body);
//		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UtenteLoginPayload body)
			throws NotFoundException {
		Utente user = usersService.findByEmail(body.getEmail());
		if (!body.getPassword().matches(user.getPassword()))
			throw new UnauthorizedException("Credenziali non valide");
		String token = JWTTools.createToken(user);
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}