package umgc.city.team1.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

        public ExceptionResponse(String messageInc, Date dateNowInc, String uriInc, String statusInc, String errorInc) {
        }
}

