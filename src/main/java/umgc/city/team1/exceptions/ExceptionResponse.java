package umgc.city.team1.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

        private String message;
        private Date timestamp;
        private String requestedURI;
        private String status;
        private String error;
}
