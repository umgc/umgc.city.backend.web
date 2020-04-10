package umgc.city.team1.utilities;

import com.sendgrid.*;
import umgc.city.team1.models.outgoing.EmailInfo;
import umgc.city.team1.models.outgoing.EmailStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SendGridEmailService implements EmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final String aPIKey = "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";//"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"; ////TODO Move to configuration
    private final SendGrid sendgridClient = new SendGrid(aPIKey);

    @Override
    public boolean sendEmail(EmailInfo emailInfo) {
        //Send email
        EmailStatus emailStatus = sendGridAPI(emailInfo);

        return emailStatus.isSent();
    }

    @Override
    public boolean sendEmail(String senderEmail, String recipientEmail, String subject, String body) {
        //Construct EmailInfo object and redirect to sendEmail(emailInfo)
        EmailInfo emailInfo = new EmailInfo();

        emailInfo.setSenderEmail(senderEmail);

        emailInfo.setRecipientEmail(recipientEmail);

        emailInfo.setSubject(subject);

        emailInfo.setBody(body);

        return sendEmail(emailInfo);
    }

    private EmailStatus sendGridAPI(EmailInfo email) {
        var emailStatus = new EmailStatus();

        //Populate SendGrid required parameters
        Email senderEmail = new Email(email.getSenderEmail(), email.getSenderName());

        Email recipientEmail = new Email(email.getRecipientEmail(), email.getRecipientName());

        Content content = new Content("text/plain", email.getBody());

        Mail mail = new Mail(senderEmail, email.getSubject(), recipientEmail, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);

            request.setEndpoint("mail/send");

            request.setBody(mail.build());

            //Get SendGrid response
            Response response = sendgridClient.api(request);

            //SendGrid's sent emails come back with a status code of 202
            if (response.getStatusCode() != 202) { ////TODO refactor if Java includes a success response method to replace the status code
                emailStatus.setSent(false);

                emailStatus.setErrorMessage("Emailed failed to send. Error code: " + response.getStatusCode());
            }

            emailStatus.setSent(true);
        } catch (IOException ex) {
            logger.error(ex.getMessage());

            emailStatus.setSent(false);

            emailStatus.setErrorMessage("Emailed failed to send. Error code: " + ex.getMessage());
        }
        return emailStatus;
    }
}
