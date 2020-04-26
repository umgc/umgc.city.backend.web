package umgc.city.team1.models.outgoing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailStatus {
    private boolean isSent;
    private String errorMessage;

    public EmailStatus(boolean isSent, String errorMessage) {
        this.isSent = isSent;
        this.errorMessage = errorMessage;
    }
}
