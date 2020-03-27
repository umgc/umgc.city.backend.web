package umgc.city.team1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailInfo {
    private String recipientEmail;
    private String recipientName;
    private String senderEmail;
    private String senderName;
    private String subject;
    private String body;
    private String contentType;
}
