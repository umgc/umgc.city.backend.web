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
@Table(name = "allowed_land_use")
public class AllowedLandUse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "zone_id", columnDefinition = "uuid")
    private UUID zoneId;

    @Column(name= "zone_land_use_id", columnDefinition = "uuid")
    private UUID zoneLaneUseId;

}
