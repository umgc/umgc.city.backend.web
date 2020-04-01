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
@Table(name = "development_standards")
public class DevelopmentStandards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "zone_id", columnDefinition = "uuid")
    private UUID zoneId;

    @Column(name = "general_standards_url")
    private String generalGtandardsURL;

    @Column(name="additional_standards_url")
    private String additionalStandardsURL;

    @Column(name="garden_standards_url")
    private String gardenStandardsURL;

    @Column(name="frontage_and_facades_standards_url")
    private String frontageAndFacadesStandardsURL;

}
