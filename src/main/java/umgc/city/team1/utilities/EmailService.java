package umgc.city.team1.utilities;

import org.springframework.stereotype.Service;
import umgc.city.team1.exceptions.EmailException;
import umgc.city.team1.models.outgoing.EmailInfo;

@Service
public interface EmailService {
    void sendEmail(EmailInfo emailInfo);

    void sendEmail(String senderEmail, String recipientEmail, String subject, String body) throws EmailException;
}

