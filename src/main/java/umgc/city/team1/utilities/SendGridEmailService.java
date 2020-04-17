package umgc.city.team1.utilities;

import com.sendgrid.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import umgc.city.team1.exceptions.EmailException;
import umgc.city.team1.models.outgoing.EmailInfo;

import java.io.IOException;

@Service
public class SendGridEmailService {
    @Value("${send-grid.api-key}") private String sendGridAPIKey;
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final SendGrid sendGridClient = new SendGrid(sendGridAPIKey);

    public void sendEmail(String senderEmail, String recipientEmail, String subject, String body) throws EmailException {
        //Construct EmailInfo object and redirect to sendEmail(emailInfo)
        EmailInfo emailInfo = new EmailInfo();

        emailInfo.setSenderEmail(senderEmail);

        emailInfo.setRecipientEmail(recipientEmail);

        emailInfo.setSubject(subject);

        emailInfo.setBody(body);

        sendGridAPI(emailInfo);
    }

    private void sendGridAPI(EmailInfo email) throws EmailException {

        //Populate SendGrid required parameters
        Email senderEmail = new Email(email.getSenderEmail(), email.getSenderName());

        Email recipientEmail = new Email(email.getRecipientEmail(), email.getRecipientName());

        Content content = new Content("text/html", email.getBody());

        Mail mail = new Mail(senderEmail, email.getSubject(), recipientEmail, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);

            request.setEndpoint("mail/send");

            request.setBody(mail.build());

            //Get SendGrid response
            Response response = sendGridClient.api(request);

            //SendGrid's sent emails come back with a status code of 202 or 200
            if (response.getStatusCode() != 202 || response.getStatusCode() !=200) {
                throw new EmailException("Email Failed: " + response.getStatusCode());
            }
        } catch (IOException | EmailException ex) {
            logger.error(ex.getMessage());
            throw new EmailException("Email Failed:" + HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
