package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* Custom exception for city not found */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CityNotFoundException extends Exception {
    public CityNotFoundException(String message){
        super(message);
    }

}

