package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such ZoneLandUse")
public class ZoneLandUseNotFoundException extends RuntimeException {
    public ZoneLandUseNotFoundException(UUID id) {
        super("Could not ZoneLandUse " + id);
    }
}
