package umgc.city.team1.models.outgoing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapCase {
    private String codeLabel;
    private String baseCode;
    private String overlayCode;
    private String baseCodeDescription;
    private String overlayCodeDescription;
    private String baseGeneralStandardsURL;
    private String baseAdditionalStandardsURL;
    private String baseGardenStandard;
    private String baseFrontageAndFacadesStandards;
    private String overlayGeneralStandardsURL;
    private String overlayAdditionalStandardsURL;
    private String overlayGardenStandards;
    private String overlayFrontageAndFacadesStandards;


    public MapCase(String codeLabel, String baseCode, String overlayCode, String baseCodeDescription, String overlayCodeDescription, String baseGeneralStandardsURL, String baseAdditionalStandardsURL, String baseGardenStandard, String baseFrontageAndFacadesStandards, String overlayGeneralStandardsURL, String overlayAdditionalStandardsURL, String overlayGardenStandards, String overlayFrontageAndFacadesStandards) {
        this.codeLabel = codeLabel;
        this.baseCode = baseCode;
        this.overlayCode = overlayCode;
        this.baseCodeDescription = baseCodeDescription;
        this.overlayCodeDescription = overlayCodeDescription;
        this.baseGeneralStandardsURL = baseGeneralStandardsURL;
        this.baseAdditionalStandardsURL = baseAdditionalStandardsURL;
        this.baseGardenStandard = baseGardenStandard;
        this.baseFrontageAndFacadesStandards = baseFrontageAndFacadesStandards;
        this.overlayGeneralStandardsURL = overlayGeneralStandardsURL;
        this.overlayAdditionalStandardsURL = overlayAdditionalStandardsURL;
        this.overlayGardenStandards = overlayGardenStandards;
        this.overlayFrontageAndFacadesStandards = overlayFrontageAndFacadesStandards;
    }

    public String getCodeLabel() {
        return codeLabel;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getOverlayCode() {
        return overlayCode;
    }

    public String getBaseCodeDescription() {
        return baseCodeDescription;
    }

    public String getOverlayCodeDescription() {
        return overlayCodeDescription;
    }

    public String getBaseGeneralStandardsURL() {
        return baseGeneralStandardsURL;
    }

    public String getBaseAdditionalStandardsURL() {
        return baseAdditionalStandardsURL;
    }

    public String getBaseFrontageAndFacadesStandards() {
        return baseFrontageAndFacadesStandards;
    }

    public String getBaseGardenStandard() {
        return baseGardenStandard;
    }

    public String getOverlayGeneralStandardsURL() {
        return overlayGeneralStandardsURL;
    }

    public String getOverlayGardenStandards() {
        return overlayGardenStandards;
    }

    public String getOverlayAdditionalStandardsURL() {
        return overlayAdditionalStandardsURL;
    }

    public String getOverlayFrontageAndFacadesStandards() {
        return overlayFrontageAndFacadesStandards;
    }
}
