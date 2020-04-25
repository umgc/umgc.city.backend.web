package umgc.city.team1.utilities;

import com.sendgrid.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import umgc.city.team1.config.MailProperties;
import umgc.city.team1.models.CityUser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Data

public class SendGridEmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final MailProperties mailProperties;
    private final Configuration freeMakerConfiguration;

    @Autowired
    public SendGridEmailService(Configuration freeMakerConfiguration, MailProperties mailProperties){
        this.freeMakerConfiguration = freeMakerConfiguration;
        this.mailProperties = mailProperties;
    }


    public void sendEmail(CityUser cityUser) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("name", cityUser.getFirstName() + " " + cityUser.getLastName());
        model.put("emailAddress", cityUser.getEmailAddress());
        model.put("password", cityUser.getPassword());
        model.put("host", mailProperties.getHomeURL());

        freeMakerConfiguration.setClassForTemplateLoading(this.getClass(), "/template");
        Template t = freeMakerConfiguration.getTemplate("email-template.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        Email senderEmail = new Email(Constants.EMAIL_SENDER);
        Email recipientEmail = new Email(cityUser.getEmailAddress());
        Content content = new Content(Constants.EMAIL_CONTENT_TYPE, htmlBody );
        //Populate SendGrid required parameters
        Mail mail = new Mail(senderEmail, Constants.EMAIL_SUBJECT, recipientEmail, content);

        SendGrid sg = new SendGrid(mailProperties.getApiKey());
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint(mailProperties.getEndpoint());
        request.setBody(mail.build());
        Response response = sg.api(request);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
    }
}



