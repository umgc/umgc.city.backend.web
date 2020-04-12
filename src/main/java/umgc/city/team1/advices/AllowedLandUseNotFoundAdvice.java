package umgc.city.team1.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import umgc.city.team1.exceptions.AllowedLandUseNotFoundException;

@ControllerAdvice
class AllowedLandUseNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AllowedLandUseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String allowedLandUseNotFoundHandler(AllowedLandUseNotFoundException ex) {
        return ex.getMessage();
    }
}