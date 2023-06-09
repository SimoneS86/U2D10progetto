package simone.U2D10progetto.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationSuccessfullPayload {
	private String accessToken;
}