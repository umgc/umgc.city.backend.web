package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class AllowedLandUseNotFoundException extends Exception {
    public AllowedLandUseNotFoundException(){
        super();
    }
}
