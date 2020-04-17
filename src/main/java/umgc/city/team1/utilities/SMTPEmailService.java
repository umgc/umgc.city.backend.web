package umgc.city.team1.utilities;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import umgc.city.team1.models.outgoing.EmailInfo;
import umgc.city.team1.models.outgoing.EmailStatus;

public class SMTPEmailService {
    @Value("${send-grid.api-key}") private String sendGridAPIKey;
    @Value("${send-grid.host}") private String host;
    @Value("${send-grid.username}") private String username;
    @Value("${send-grid.port}") private int port;

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmail(String senderEmail, String recipientEmail, String subject, String body) {
        //Construct EmailInfo object and redirect to sendEmail(emailInfo)
        EmailInfo emailInfo = new EmailInfo();

        emailInfo.setSenderEmail(senderEmail);

        emailInfo.setRecipientEmail(recipientEmail);

        emailInfo.setSubject(subject);

        emailInfo.setBody(body);

    }

    private EmailStatus smtpEmailRelay(EmailInfo emailInfo) {
        var emailStatus = new EmailStatus();

        try {
            //Populate SMTP required parameters
            Email email = EmailBuilder.startingBlank()
                    .from(emailInfo.getSenderName(), emailInfo.getSenderEmail())
                    .to(emailInfo.getRecipientName(), emailInfo.getRecipientEmail())
                    .withSubject(emailInfo.getSubject())
                    .withPlainText(emailInfo.getBody())
                    .buildEmail();

            Mailer mailer = MailerBuilder
                    .withSMTPServer(host, port, username, sendGridAPIKey)
                    .buildMailer();

            mailer.sendMail(email);

            emailStatus.setSent(true);

        } catch (Exception ex) {
            logger.error(ex.getMessage());

            emailStatus.setSent(false);

            emailStatus.setErrorMessage("Emailed failed to send. Error code: " + ex.getMessage());
        }
        return emailStatus;
    }
}
