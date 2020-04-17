package umgc.city.team1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="email")
@Data
public class MailProperties {
    private String apiKey;
    private String host;
    private int port;
    private String username;
    private String homeURL;
}
