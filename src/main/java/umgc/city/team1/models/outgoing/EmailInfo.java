package umgc.city.team1.models.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailInfo {
    private String recipientEmail;
    private String recipientName;
    private String senderEmail;
    private String senderName;
    private String subject;
    private String body;
    private String contentType;

    public EmailInfo(String recipientEmail, String recipientName, String senderEmail, String senderName, String subject, String body, String contentType) {
        this.recipientEmail = recipientEmail;
        this.recipientName = recipientName;
        this.senderEmail = senderEmail;
        this.senderName = senderName;
        this.subject = subject;
        this.body = body;
        this.contentType = contentType;
    }
}
