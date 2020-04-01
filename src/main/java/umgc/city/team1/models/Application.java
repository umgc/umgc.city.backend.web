package umgc.city.team1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "permit_id", columnDefinition = "uuid")
    private UUID permitId;

    @Column(name = "name")
    private String name;

    @Column(name = "application_url")
    private String applicationURL;
}
