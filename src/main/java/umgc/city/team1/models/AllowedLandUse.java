package umgc.city.team1.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "allowed_land_use")
public class AllowedLandUse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @Column(name = "permit_name")
    private String permitName;

    @Column(name ="permit_description")
    private String permitDescription;

    @Column(name = "procedure_url")
    private String procedureUrl;

    @Column(name = "application_url")
    private String applicationUrl;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_land_use_id", nullable = false)
    private ZoneLandUse zoneLandUse;


}
