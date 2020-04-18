package umgc.city.team1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "development_standards")
public class DevelopmentStandards implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "general_standard_url")
    @NotNull
    private String generalStandardsURL;

    @Column(name="additional_standard_url")
    @NotNull
    private String additionalStandardsURL;

    @Column(name="garden_standard_url")
    @NotNull
    private String gardenStandardsURL;

    @Column(name="frontage_and_facades_standards_url")
    @NotNull
    private String frontageAndFacadesStandardsURL;

    @OneToOne
    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    @JsonIgnore
    private Zone zone;

    public DevelopmentStandards(String generalStandardsURL, String additionalStandardsURL,
                                String gardenStandardsURL, String frontageAndFacadesStandardsURL, Zone zone){
        this.generalStandardsURL = generalStandardsURL;
        this.additionalStandardsURL = additionalStandardsURL;
        this.frontageAndFacadesStandardsURL = frontageAndFacadesStandardsURL;
        this.gardenStandardsURL = gardenStandardsURL;
        this.zone = zone;
    }

}
