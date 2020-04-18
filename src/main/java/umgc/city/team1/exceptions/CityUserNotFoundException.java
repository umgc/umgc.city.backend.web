package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such CityUser")
public class CityUserNotFoundException extends RuntimeException {
    public CityUserNotFoundException(UUID id) {
        super("User Not Found: " + id);
    }
    public CityUserNotFoundException(String userData) {
        super("User Not Found:" + userData);
    }
}
