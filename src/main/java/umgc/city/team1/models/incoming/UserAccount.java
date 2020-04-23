package umgc.city.team1.models.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserAccount implements Serializable {

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String state;

    private String password;

    private UUID authoritiesId;

    public UserAccount(String firstName, String lastName, String email, String city, String state, String password,
                       UUID authoritiesId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.state = state;
        this.password = password;
        this.authoritiesId = authoritiesId;
    }

    public UserAccount(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserAccount(String email){
        this.email = email;
    }
}
