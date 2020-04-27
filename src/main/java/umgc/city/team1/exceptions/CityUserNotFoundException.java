package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* Custom exception for city user not found */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CityUserNotFoundException extends RuntimeException{
    public CityUserNotFoundException(String message){
        super(message);
    }

}
