package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ZoneNotFoundException extends RuntimeException {
    public ZoneNotFoundException(UUID id){
        super(String.format("Zone with id: %s not found", id.toString()));
    }
    public ZoneNotFoundException(String message, UUID id) {
        super(String.format(message+": %s not found", id.toString()));
    }
}


