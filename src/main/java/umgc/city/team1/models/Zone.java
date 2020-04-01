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
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "city_id", columnDefinition = "uuid")
    private UUID cityId;

    @Column(name = "zone_symbol")
    private String zoneSymbol;

    @Column(name= "description")
    private String description;

}
