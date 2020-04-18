package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CityNotFoundException extends Exception {
    public CityNotFoundException(){
        super();
    }
    public CityNotFoundException(String message){
        super(message);
    }
    public CityNotFoundException(Throwable e){
        super(e);
    }
    public CityNotFoundException(String message, Throwable e){
        super(message, e);
    }
}

