package umgc.city.team1.utilities;

import com.sendgrid.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import umgc.city.team1.exceptions.EmailException;
import umgc.city.team1.models.outgoing.EmailInfo;

import java.io.IOException;

@Service
@Data
public class SendGridEmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmail(EmailInfo email, String sendGridAPIKey) throws EmailException {

        //Populate SendGrid required parameters
        Email senderEmail = new Email(email.getSenderEmail(), email.getSenderName());

        Email recipientEmail = new Email(email.getRecipientEmail(), email.getRecipientName());

        Content content = new Content(email.getContentType(), email.getBody());

        Mail mail = new Mail(senderEmail, email.getSubject(), recipientEmail, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);

            request.setEndpoint(Constants.REQUEST_ENDPOINT);

            request.setBody(mail.build());

            //Get SendGrid response
            SendGrid sendGridClient = new SendGrid(sendGridAPIKey);
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
