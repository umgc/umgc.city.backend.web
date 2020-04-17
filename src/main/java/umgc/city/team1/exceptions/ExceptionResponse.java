package umgc.city.team1.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@Component
public class ExceptionResponse {

        private String message;
        private Date timestamp;
        private String requestedURI;
        private String status;
        private String error;
    }

