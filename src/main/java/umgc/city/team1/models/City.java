package umgc.city.team1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name ="city_user_id", columnDefinition = "uuid")
    private UUID cityUserId;

    @Column(name="name")
    private String name;

    @Column(name="state")
    private String state;
}
