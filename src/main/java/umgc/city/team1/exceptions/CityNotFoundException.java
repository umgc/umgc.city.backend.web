package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such City")
public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(UUID id) {
        super("Could not find City " + id);
    }
}

