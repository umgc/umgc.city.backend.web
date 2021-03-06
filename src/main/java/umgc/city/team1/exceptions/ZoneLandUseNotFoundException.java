package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ZoneLandUseNotFoundException extends Exception {
    public ZoneLandUseNotFoundException(String message, Throwable e){
        super(message, e);
    }
}
