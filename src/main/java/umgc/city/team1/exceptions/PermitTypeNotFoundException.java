package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Permit Type")
public class PermitTypeNotFoundException extends RuntimeException {
    public PermitTypeNotFoundException(UUID id) {
        super("Could not find Permit Type " + id);
    }
}