package umgc.city.team1.models.incoming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UseCaseDto {

    private final String zoneSymbol;
    private final String zoneDescription;
    private final String applicationURL;
    private final String procedureURL;
    private final String permitName;
    private final String permitDescription;
    private final UUID zoneLandUseId;
    private final String additionalStandardURL;
    private final String zoneLandUseDescription;
    private final String generalStandardURL;
    private final String gardenStandardURL;
    private final String frontageAndFacadesStandardURL;
    private final UUID cityId;
    private final UUID zoneId;

    public UseCaseDto(UUID cityId, UUID zoneId, String zoneSymbol, String zoneDescription, String applicationURL, String procedureURL, String permitName, String permitDescription, UUID zoneLandUseId, String zoneLandUseDescription, String generalStandardURL, String gardenStandardURL, String additionalStandardURL, String frontageAndFacadesStandardURL) {
        this.zoneSymbol = zoneSymbol;
        this.zoneDescription = zoneDescription;
        this.applicationURL = applicationURL;
        this.procedureURL = procedureURL;
        this.permitName = permitName;
        this.permitDescription = permitDescription;
        this.zoneLandUseId = zoneLandUseId;
        this.zoneLandUseDescription = zoneLandUseDescription;
        this.generalStandardURL = generalStandardURL;
        this.gardenStandardURL = gardenStandardURL;
        this.additionalStandardURL = additionalStandardURL;
        this.frontageAndFacadesStandardURL = frontageAndFacadesStandardURL;
        this.cityId = cityId;
        this.zoneId = zoneId;
    }
}
