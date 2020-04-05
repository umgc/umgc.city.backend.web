package umgc.city.team1.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import umgc.city.team1.exceptions.PermitTypeNotFoundException;

@ControllerAdvice
class PermitTypeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PermitTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String permitTypeNotFoundHandler(PermitTypeNotFoundException ex) {
        return ex.getMessage();
    }
}