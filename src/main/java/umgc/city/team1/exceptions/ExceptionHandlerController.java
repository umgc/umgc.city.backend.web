package umgc.city.team1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@RestControllerAdvice
@Data
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private ExceptionResponse exceptionResponse;

    @ExceptionHandler(value = ZoneNotFoundException.class)
    public ResponseEntity<ExceptionResponse> ZoneNotFoundError(ZoneNotFoundException exception,
                                                               HttpServletRequest request ){
        exceptionResponse = getExceptionResponse(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.name(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ZoneLandUseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> ZoneLandUseNotError(ZoneLandUseNotFoundException exception,
                                                               HttpServletRequest request ){
        exceptionResponse = getExceptionResponse(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.name(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AllowedLandUseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> AllowedLandUseNotError(AllowedLandUseNotFoundException exception,
                                                                 HttpServletRequest request ){
        exceptionResponse = getExceptionResponse(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.name(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> CityNotFoundError(CityNotFoundException exception,
                                                                    HttpServletRequest request ){
        exceptionResponse = getExceptionResponse(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.name(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CityUserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> CityUserNotFoundError(CityUserNotFoundException exception,
                                                                    HttpServletRequest request ){
        exceptionResponse = getExceptionResponse(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.name(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DevelopmentStandardsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> DevelopmentStandardsNotFoundFoundError(DevelopmentStandardsNotFoundException exception,
                                                                    HttpServletRequest request ){
        exceptionResponse = getExceptionResponse(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.name(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmailException.class)
    public ResponseEntity<ExceptionResponse> EmailError(EmailException exception,
                                                                                    HttpServletRequest request ){
        exceptionResponse = getExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UseCaseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> UseCaseNotFoundError(UseCaseNotFoundException exception,
                                                        HttpServletRequest request ){
        exceptionResponse = getExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ObjectCreationFailedException.class)
    public ResponseEntity<ExceptionResponse> ObjectCreationFailedError(ObjectCreationFailedException exception,
                                                                  HttpServletRequest request ){
        exceptionResponse = getExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ExceptionResponse getExceptionResponse(String status, String error, String message, String uri) {
        return new ExceptionResponse(message, getDateNow(), uri, status, error);
    }

    private Date getDateNow() {
        return Date.from(Instant.now());
    }
}


