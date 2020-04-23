package umgc.city.team1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="email")
@Component
@Data
public class MailProperties {
    private String apiKey;
    private String host;
    private int port;
    private String username;
    private String homeURL;
    private String endpoint;
}
