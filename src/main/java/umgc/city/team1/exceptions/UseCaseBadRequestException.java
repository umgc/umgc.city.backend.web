package umgc.city.team1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class UseCaseBadRequestException extends Exception {
    public UseCaseBadRequestException(String message) {
        super("Invalid data: " + message);
    }
}
