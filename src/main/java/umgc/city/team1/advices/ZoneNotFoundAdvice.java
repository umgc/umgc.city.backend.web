package umgc.city.team1.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import umgc.city.team1.exceptions.ZoneNotFoundException;


@ControllerAdvice
class ZoneNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ZoneNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String zoneNotFoundHandler(ZoneNotFoundException ex) {
        return ex.getMessage();
    }
}