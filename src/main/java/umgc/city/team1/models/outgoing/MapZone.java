package umgc.city.team1.models.outgoing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapZone {
    private String zoneSymbol;
    private String description;
    private String generalStandardsURL;
    private String additionalStandardsURL;
    private String gardenStandardsURL;
    private String frontageAndFacadesStandardsURL;

    public MapZone(String zoneSymbol, String description, String generalStandardsURL, String additionalStandardsURL, String gardenStandardsURL, String frontageAndFacadesStandardsURL) {
        this.zoneSymbol = zoneSymbol;
        this.description = description;
        this.generalStandardsURL = generalStandardsURL;
        this.additionalStandardsURL = additionalStandardsURL;
        this.gardenStandardsURL = gardenStandardsURL;
        this.frontageAndFacadesStandardsURL = frontageAndFacadesStandardsURL;
    }
}
