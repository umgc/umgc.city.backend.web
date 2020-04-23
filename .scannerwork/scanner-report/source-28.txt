package umgc.city.team1.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class DevelopmentStandardsNotFoundException extends Exception {
    public DevelopmentStandardsNotFoundException() {
        super();
    }

    public DevelopmentStandardsNotFoundException(String message) {
        super(message);
    }

    public DevelopmentStandardsNotFoundException(Throwable e) {
        super(e);
    }

    public DevelopmentStandardsNotFoundException(String message, Throwable e) {
        super(message, e);
    }
}
