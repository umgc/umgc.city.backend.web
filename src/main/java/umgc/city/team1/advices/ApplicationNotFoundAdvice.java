package umgc.city.team1.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import umgc.city.team1.exceptions.AllowedLandUseNotFoundException;
import umgc.city.team1.exceptions.ApplicationNotFoundException;
import umgc.city.team1.models.Application;

@ControllerAdvice
class ApplicationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ApplicationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String applicationNotFoundHandler(ApplicationNotFoundException ex) {
        return ex.getMessage();
    }
}