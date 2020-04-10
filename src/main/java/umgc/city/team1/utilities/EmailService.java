package umgc.city.team1.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import umgc.city.team1.models.outgoing.EmailInfo;

@Service
public interface EmailService {
    final Logger logger = LoggerFactory.getLogger(EmailService.class);
    boolean sendEmail(EmailInfo emailInfo);

    boolean sendEmail(String senderEmail, String recipientEmail, String subject, String body);
}

