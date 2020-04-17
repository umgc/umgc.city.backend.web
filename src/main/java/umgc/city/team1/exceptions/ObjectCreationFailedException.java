package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ObjectCreationFailedException extends Exception {
    public ObjectCreationFailedException(){
        super();
    }
    public ObjectCreationFailedException(String message){
        super(message);
    }
    public ObjectCreationFailedException(Throwable e){
        super(e);
    }
    public ObjectCreationFailedException(String message, Throwable e){
        super(message, e);
    }
}
