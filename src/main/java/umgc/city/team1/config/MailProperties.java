package umgc.city.team1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="email")
@Component
@Data
public class MailProperties {

    public MailProperties(String apiKey, String host, int port, String username, String homeURL, String endpoint) {
    }
}
