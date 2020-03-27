package umgc.city.team1.utilities;

import umgc.city.team1.models.EmailInfo;

public interface EmailService {
    boolean sendEmail(EmailInfo emailInfo);

    boolean sendEmail(String senderEmail, String recipientEmail, String subject, String body);
}
