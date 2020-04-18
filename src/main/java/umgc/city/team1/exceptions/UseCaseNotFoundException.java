package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class UseCaseNotFoundException extends Exception {
    public UseCaseNotFoundException(){
        super();
    }
    public UseCaseNotFoundException(String message){
        super(message);
    }
    public UseCaseNotFoundException(Throwable e){
        super(e);
    }
    public UseCaseNotFoundException(String message, Throwable e){
        super(message, e);
    }
}
