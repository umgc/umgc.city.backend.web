package umgc.city.team1.models.incoming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String state;
    private String password;
    private UUID authoritiesId;
}
