package umgc.city.team1.utilities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import umgc.city.team1.config.MailProperties;
import umgc.city.team1.models.outgoing.EmailInfo;
import umgc.city.team1.models.outgoing.EmailStatus;

@Data
@Service
@RequiredArgsConstructor
public class SMTPEmailService {


    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private  final MailProperties mailProperties;

    public void sendEmail(String senderEmail, String recipientEmail, String subject, String body) {
        //Construct EmailInfo object and redirect to sendEmail(emailInfo)
        EmailInfo emailInfo = new EmailInfo(recipientEmail, recipientName, senderEmail, senderName, subject, body, contentType);

        emailInfo.setSenderEmail(senderEmail);

        emailInfo.setRecipientEmail(recipientEmail);

        emailInfo.setSubject(subject);

        emailInfo.setBody(body);

    }

    private EmailStatus smtpEmailRelay(EmailInfo emailInfo) {
        var emailStatus = new EmailStatus(isSent, errorMessage);

        try {
            //Populate SMTP required parameters
            Email email = EmailBuilder.startingBlank()
                    .from(emailInfo.getSenderName(), emailInfo.getSenderEmail())
                    .to(emailInfo.getRecipientName(), emailInfo.getRecipientEmail())
                    .withSubject(emailInfo.getSubject())
                    .withPlainText(emailInfo.getBody())
                    .buildEmail();

            Mailer mailer = MailerBuilder
                    .withSMTPServer(mailProperties.getHost(), mailProperties.getPort(), mailProperties.getUsername(),
                            mailProperties.getApiKey())
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
