package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class AllowedLandUseNotFoundException extends Exception {
    public AllowedLandUseNotFoundException(){
        super();
    }
    public AllowedLandUseNotFoundException(String message){
        super(message);
    }
    public AllowedLandUseNotFoundException(Throwable e){
        super(e);
    }
    public AllowedLandUseNotFoundException(String message, Throwable e){
        super(message, e);
    }
}
