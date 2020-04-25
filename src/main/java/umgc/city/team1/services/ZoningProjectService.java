package umgc.city.team1.services;

import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umgc.city.team1.exceptions.*;
import umgc.city.team1.models.*;
import umgc.city.team1.models.incoming.MapShape;
import umgc.city.team1.models.incoming.UseCaseDto;
import umgc.city.team1.models.incoming.UserAccount;
import umgc.city.team1.models.outgoing.MapCase;
import umgc.city.team1.models.outgoing.MapZone;
import umgc.city.team1.repositories.*;
import umgc.city.team1.utilities.SendGridEmailService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class ZoningProjectService {

    private final Logger logger = LoggerFactory.getLogger(ZoningProjectService.class);
    private CityRepository cityRepository;
    private CityUserRepository cityUserRepository;
    private ZoneRepository zoneRepository;
    private ZoneLandUseRepository zoneLandUseRepository;
    private AllowedLandUseRepository allowedLandUseRepository;
    private DevelopmentStandardsRepository developmentStandardsRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private SendGridEmailService sendGridEmailService;


    public CityUser createUserAccount(UserAccount userAccount) throws ObjectCreationFailedException {
        try {
            Authorities authorities = authoritiesRepository.getAuthorityByName("City_Official");
            CityUser cityUser = new CityUser(userAccount.getFirstName(), userAccount.getLastName(), userAccount.getEmail(), userAccount.getPassword(), authorities.getId());
            cityRepository.save(new City(userAccount.getCity(), userAccount.getState(),
                    cityUserRepository.save(cityUser)));
            return cityUser;
        }catch (Exception e){
            throw new ObjectCreationFailedException(e);
        }
    }

    public UserAccount VerifyUserAccount(UserAccount userAccount) throws CityUserNotFoundException {
        try {
            CityUser cityUser;
            if (!userAccount.getEmail().isEmpty()) {
                cityUser = cityUserRepository.getUserByEmail(userAccount.getEmail());
                if (cityUser == null ) throw new CityUserNotFoundException(userAccount.getEmail());

                if (!cityUser.getPassword().equals(userAccount.getPassword())) throw new CityUserNotFoundException(userAccount.getEmail());

                UserAccount existingUser = new UserAccount();
                existingUser.setEmail(cityUser.getEmailAddress());
                existingUser.setCity(cityUser.getCity().getId().toString());
                return existingUser;
            }
        } catch (Exception e){
            throw new CityUserNotFoundException(userAccount.getEmail());
        }

        return null;
    }

    public List<Zone> getZonesByCityId(UUID cityId) throws ZoneNotFoundException {
        return Optional.of(zoneRepository.findAllByCity(cityId))
                .orElseThrow(() -> new ZoneNotFoundException("Zones were not found with city id" + cityId));
    }

    public List<UseCaseDto> getUseCasesByZone(UUID zoneId) throws UseCaseNotFoundException {
        return Optional.of(zoneRepository.findUseCaseByZoneId(zoneId)).orElseThrow(() -> new UseCaseNotFoundException(
                "Land Use Cases could not be found for zone with Id: " + zoneId));
    }

    public List<UseCaseDto> getUseCasesByCity(UUID cityId) throws UseCaseNotFoundException {
        return Optional.of(zoneRepository.findUseCaseByCityId(cityId)).orElseThrow(() -> new UseCaseNotFoundException(
                "Land Use Cases could not be found for city with Id: " + cityId));
    }

    public void sendAdminUserCredentialsInEmail(UserAccount userAccount) throws CityUserNotFoundException,
            IOException, TemplateException {
        Optional<String> emailAddressOptional = Optional.ofNullable(userAccount.getEmail());
        Optional<String> firstNameOptional = Optional.ofNullable(userAccount.getFirstName());
        Optional<String> lastNameOptional = Optional.ofNullable(userAccount.getLastName());
        try {
            if (emailAddressOptional.isPresent()) {
                String emailAddress = emailAddressOptional.get();
                CityUser cityUser = cityUserRepository.getUserByEmail(emailAddress);
                sendGridEmailService.sendEmail(cityUser);
            }
            if (firstNameOptional.isPresent() && lastNameOptional.isPresent()) {
                String firstName = firstNameOptional.get();
                String lastName = lastNameOptional.get();
                CityUser cityUser = cityUserRepository.getUserByName(firstName, lastName);
                sendGridEmailService.sendEmail(cityUser);
            } else {
                throw new CityUserNotFoundException("User Credentials Not Found!");
            }
        } catch (NullPointerException e) {
            throw new CityUserNotFoundException("User Credentials Not Found!");
        }
    }

    public Optional<City> getCityById(UUID cityId) throws CityNotFoundException {
        return Optional.of(this.cityRepository.findById(cityId))
                .orElseThrow(() -> new CityNotFoundException("City with id " + cityId + " not found"));
    }

    private Optional<ZoneLandUse> getZoneLandUseByDescription(String zoneLandUseDescription, UUID cityId) {
        return this.zoneLandUseRepository.findByZoneLandUseByDescription(zoneLandUseDescription, cityId);
    }

    private Optional<AllowedLandUse> getAllowedLandUseByZoneAndZoneLandUSe(UUID zoneId, UUID zoneLandUseId) {
        return this.allowedLandUseRepository.findAllByZoneandZoneLandUse(zoneId, zoneLandUseId);
    }

    public void createUseCase(UseCaseDto useCaseDto) throws CityNotFoundException, UseCaseNotFoundException {
        Optional<City> cityOptional = getCityById(useCaseDto.getCityId());
        if (cityOptional.isEmpty())
            throw new CityNotFoundException("City with id " + useCaseDto.getCityId() + " not found");
        City city = cityOptional.get();

        //Get zone by zone symbol
        Zone zone;
        Optional<Zone> zoneOptional = Optional.ofNullable(zoneRepository.
                getZoneSymbolAndCityId(useCaseDto.getZoneSymbol(), city.getId()));
        zone = zoneOptional.orElseGet(() -> new Zone(useCaseDto.getZoneSymbol(), useCaseDto.getZoneDescription(),
                city));
        zoneRepository.save(zone);

        //Construct DevelopmentStandards and save it to the database
        DevelopmentStandards developmentStandards = new DevelopmentStandards(useCaseDto.getGeneralStandardURL(),
                useCaseDto.getAdditionalStandardURL(), useCaseDto.getGardenStandardURL(),
                useCaseDto.getFrontageAndFacadesStandardURL(), zone);
        developmentStandardsRepository.save(developmentStandards);

        //Get zoneLandUseOptional by Description
        Optional<ZoneLandUse> zoneLandUseOptional =
                getZoneLandUseByDescription(useCaseDto.getZoneLandUseDescription(), city.getId());
        ZoneLandUse zoneLandUse;
        if (zoneLandUseOptional.isEmpty()) {
            //Construct ZoneLandUse and save it to the database
            zoneLandUse = new ZoneLandUse(useCaseDto.getZoneLandUseDescription(), city);
            zoneLandUseRepository.save(zoneLandUse);
        } else {
            zoneLandUse = zoneLandUseOptional.get();
        }

        //Get AllowedLandUse by zone and landUse
        Optional<AllowedLandUse> allowedLandUseOptional = getAllowedLandUseByZoneAndZoneLandUSe(zone.getId(),
                zoneLandUse.getId());
        if (allowedLandUseOptional.isPresent())
            throw new UseCaseNotFoundException("Use Case Already Exists");
        AllowedLandUse allowedLandUse = new AllowedLandUse(useCaseDto.getPermitName(),
                useCaseDto.getPermitDescription(), useCaseDto.getProcedureURL(), useCaseDto.getApplicationURL(),
                zoneLandUse, zone);
        allowedLandUseRepository.save(allowedLandUse);

    }


    public void editUseCase(UseCaseDto useCaseDto) throws UseCaseNotFoundException {

        Optional<Zone> zoneOptional = Optional.of(zoneRepository.findById(useCaseDto.getZoneId()))
                .orElseThrow(() -> new UseCaseNotFoundException("Use Case Does Not Exist with Zone Id: " + useCaseDto.getZoneId()));
        //Get zone by id
        //noinspection OptionalGetWithoutIsPresent already checked above
        Zone zone = zoneOptional.get();
        zone.setDescription(useCaseDto.getZoneDescription());
        zone.setZoneSymbol(useCaseDto.getZoneSymbol());
        zoneRepository.save(zone);

        //Construct DevelopmentStandards and save it to the database
        Optional<DevelopmentStandards> developmentStandardsOptional =
                Optional.of(developmentStandardsRepository.findByZoneId(useCaseDto.getZoneId()))
                        .orElseThrow(() -> new UseCaseNotFoundException("Use Case Invalid: No Existing Development " +
                                "Standards " +
                                "Exist for Zone Id: " + useCaseDto.getZoneId()));

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        DevelopmentStandards developmentStandards = developmentStandardsOptional.get();
        developmentStandards.setAdditionalStandardsURL(useCaseDto.getAdditionalStandardURL());
        developmentStandards.setFrontageAndFacadesStandardsURL(useCaseDto.getFrontageAndFacadesStandardURL());
        developmentStandards.setGardenStandardsURL(useCaseDto.getGardenStandardURL());
        developmentStandards.setGeneralStandardsURL(useCaseDto.getGeneralStandardURL());
        developmentStandardsRepository.save(developmentStandards);

        //Get zoneLandUseOptional by Description
        Optional<ZoneLandUse> zoneLandUseOptional =
                Optional.of(zoneLandUseRepository.findById(useCaseDto.getZoneLandUseId()))
                        .orElseThrow(() -> new UseCaseNotFoundException("Use Case Invalid: No Existing Zone Land Use " +
                                "Exist for Zone Id: " + useCaseDto.getZoneId()));

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        ZoneLandUse zoneLandUse = zoneLandUseOptional.get();
        //Construct ZoneLandUse and save it to the database
        zoneLandUse.setDescription(useCaseDto.getZoneLandUseDescription());
        zoneLandUseRepository.save(zoneLandUse);

        //Get AllowedLandUse by zone and landUse
        Optional<AllowedLandUse> allowedLandUseOptional =
                Optional.of(allowedLandUseRepository.findAllByZoneandZoneLandUse(useCaseDto.getZoneId(),
                        useCaseDto.getZoneLandUseId()))
                        .orElseThrow(() -> new UseCaseNotFoundException("Use Case Invalid: No Existing Allowed " +
                                "Land Use Case Exist for Zone Id: " + useCaseDto.getZoneId() + " and " + useCaseDto.getZoneLandUseId()));

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        AllowedLandUse allowedLandUse = allowedLandUseOptional.get();
        allowedLandUse.setApplicationUrl(useCaseDto.getApplicationURL());
        allowedLandUse.setPermitDescription(useCaseDto.getPermitDescription());
        allowedLandUse.setPermitName(useCaseDto.getPermitName());
        allowedLandUse.setProcedureUrl(useCaseDto.getProcedureURL());
        allowedLandUseRepository.save(allowedLandUse);
    }

    public MapCase getPasadenaZoneData(MapShape mapShape) throws ZoneNotFoundException {
        MapZone baseZoneOptional =
                Optional.of(zoneRepository.findUseCaseByZoneSymbol(mapShape.getZoneCode())).orElseThrow(() ->
                        new ZoneNotFoundException("Zone with code: " + mapShape.getZoneCode() + " not found"));
        MapCase mapCase = new MapCase();
        mapCase.setCodeLabel(mapShape.getCodeLabel());
        mapCase.setBaseCode(mapShape.getZoneCode());
        mapCase.setBaseCodeDescription(baseZoneOptional.getDescription());
        mapCase.setBaseGeneralStandardsURL(baseZoneOptional.getGeneralStandardsURL());
        mapCase.setBaseAdditionalStandardsURL(baseZoneOptional.getAdditionalStandardsURL());
        mapCase.setBaseGardenStandard(baseZoneOptional.getGardenStandardsURL());
        mapCase.setBaseFrontageAndFacadesStandards(baseZoneOptional.getFrontageAndFacadesStandardsURL());

        if (!mapShape.getOverlayCode().isEmpty()) {
            if (mapShape.getOverlayCode().equals("PK-LD")) {
                mapCase.setOverlayCode("PK-LD");
                mapCase.setOverlayCodeDescription("Parking and Landmark Overlay District");
                mapCase.setOverlayGeneralStandardsURL("https://library.municode" +
                        ".com/ca/pasadena/codes/code_of_ordinances?nodeId=TIT17_ZONING_CODE_ART2ZODIALLAUSZOECST_CH17" +
                        ".28OVZODI");
            }
            if (mapShape.getOverlayCode().equals("OC-LD")) {
                mapCase.setOverlayCode("OC-LD");
                mapCase.setOverlayCodeDescription("Office Conversion Overlay District and Landmark Overlay District");
                mapCase.setOverlayGeneralStandardsURL("https://library.municode" +
                        ".com/ca/pasadena/codes/code_of_ordinances?nodeId=TIT17_ZONING_CODE_ART2ZODIALLAUSZOECST_CH17" +
                        ".28OVZODI");
            }
            if (mapShape.getOverlayCode().equals("HD-LD")) {
                mapCase.setOverlayCode("HD-LD");
                mapCase.setOverlayCodeDescription("Hillside Development (Residential) and Landmark Overlay District");
                mapCase.setOverlayGeneralStandardsURL("https://library.municode" +
                        ".com/ca/pasadena/codes/code_of_ordinances?nodeId=TIT17_ZONING_CODE_ART2ZODIALLAUSZOECST_CH17" +
                        ".28OVZODI");
            }
            if (mapShape.getOverlayCode().equals("OC-HL-1")) {
                mapCase.setOverlayCode("OC-HL-1");
                mapCase.setOverlayCodeDescription("Office Conversion (Multi-family Residential) and Landmark Overlay " +
                        "District");
                mapCase.setOverlayGeneralStandardsURL("https://library.municode" +
                        ".com/ca/pasadena/codes/code_of_ordinances?nodeId=TIT17_ZONING_CODE_ART2ZODIALLAUSZOECST_CH17" +
                        ".28OVZODI");
            } else {
                MapZone overlayZoneOptional =
                        Optional.of(zoneRepository.findUseCaseByZoneSymbol(mapShape.getZoneCode())).orElseThrow(() ->
                                new ZoneNotFoundException("Zone with code: " + mapShape.getZoneCode() + " not found"));
                mapCase.setOverlayCode(overlayZoneOptional.getZoneSymbol());
                mapCase.setOverlayCodeDescription(overlayZoneOptional.getZoneSymbol());
                mapCase.setOverlayGeneralStandardsURL(overlayZoneOptional.getGeneralStandardsURL());
                mapCase.setOverlayAdditionalStandardsURL(overlayZoneOptional.getAdditionalStandardsURL());
                mapCase.setOverlayFrontageAndFacadesStandards(overlayZoneOptional.getFrontageAndFacadesStandardsURL());
            }
        }
        return mapCase;
    }


}

