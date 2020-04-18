package umgc.city.team1.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import umgc.city.team1.config.MailProperties;
import umgc.city.team1.exceptions.*;
import umgc.city.team1.models.*;
import umgc.city.team1.models.incoming.UseCaseDto;
import umgc.city.team1.models.incoming.UserAccount;
import umgc.city.team1.models.outgoing.EmailInfo;
import umgc.city.team1.repositories.*;
import umgc.city.team1.utilities.Constants;
import umgc.city.team1.utilities.SendGridEmailService;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Data
public class ZoningProjectService {

    private final Logger logger = LoggerFactory.getLogger(ZoningProjectService.class);
    private final CityUserRepository cityUserRepository;
    private final CityRepository cityRepository;
    private final ZoneRepository zoneRepository;
    private final AllowedLandUseRepository allowedLandUseRepository;
    private final DevelopmentStandardsRepository developmentStandardsRepository;
    private final ZoneLandUseRepository zoneLandUseRepository;
    private  final MailProperties mailProperties;
    private Configuration freemarkerConfig;

    public HttpStatus createUserAccount(UserAccount userAccount) throws ObjectCreationFailedException {
        try {
            cityRepository.save(new City(userAccount.getCity(), userAccount.getState(),
                    cityUserRepository.save(new CityUser(userAccount.getFirstName(), userAccount.getLastName(),
                            userAccount.getEmail(), userAccount.getPassword(), userAccount.getAuthoritiesId()))));
        }catch (Exception e){
            throw new ObjectCreationFailedException(e);
        }
        return HttpStatus.CREATED;
    }

    public List<Zone> getZonesByCityId(UUID cityId) throws ZoneNotFoundException {
        return Optional.of(zoneRepository.findAllByCity(cityId))
                .orElseThrow(() -> new ZoneNotFoundException("Zones were not found with city id" + cityId));
    }

    public Page<AllowedLandUse> getAllowedLandUsesByZoneId(UUID zoneId, Pageable pageable) throws AllowedLandUseNotFoundException {
        return Optional.of(allowedLandUseRepository.findByZoneId(zoneId,
                pageable)).orElseThrow(() -> new AllowedLandUseNotFoundException("Zones could not be found for city " +
                "with Id: " + zoneId));
    }

    public Zone getZonesById(UUID zoneId) throws ZoneNotFoundException {
        return Optional.of(this.zoneRepository.getOne(zoneId))
                .orElseThrow(() -> new ZoneNotFoundException("Zone could not be found with Id:" + zoneId));
    }

    public List<UseCaseDto> getUseCasesByZone(UUID zoneId) throws UseCaseNotFoundException {
        return Optional.of(zoneRepository.findUseCaseByZoneId(zoneId)).orElseThrow(() -> new UseCaseNotFoundException(
                "Land Use Cases could not be found for zone with Id: " + zoneId));
    }

    public List<UseCaseDto> getUseCasesByCity(UUID cityId) throws UseCaseNotFoundException {
        return Optional.of(zoneRepository.findUseCaseByCityId(cityId)).orElseThrow(() -> new UseCaseNotFoundException(
                "Land Use Cases could not be found for city with Id: " + cityId));
    }

    public String sendAdminUserCredentialsInEmail(UserAccount userAccount) throws CityUserNotFoundException,
            EmailException {
        List<CityUser> cityUser;
        if (!userAccount.getEmail().isEmpty()) {
            cityUser = cityUserRepository.findByEmailAddress(userAccount.getEmail());
        } else {
            cityUser = Optional.of(cityUserRepository.findByFirstNameAndLastName(userAccount.getFirstName(),
                    userAccount.getLastName())).orElseThrow(() -> new CityUserNotFoundException("No User Found"));
        }
        if (cityUser.size() > 1) {
            throw new CityUserNotFoundException("More then One User Found");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("name", cityUser.get(0).getFirstName() + " " + cityUser.get(0).getLastName());
        model.put("emailAddress", cityUser.get(0).getEmailAddress());
        model.put("password", cityUser.get(0).getPassword());
        model.put("host", mailProperties.getHomeURL());

        try {

            Template t = freemarkerConfig.getTemplate("email-template.ftl");
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            SendGridEmailService sendGridEmailService = new SendGridEmailService();
            EmailInfo emailInfo = new EmailInfo(cityUser.get(0).getEmailAddress(), (cityUser.get(0).getFirstName() +
                    " " + cityUser.get(0).getLastName()), Constants.EMAIL_SENDER, Constants.EMAIL_SENDER_NAME,
                    Constants.EMAIL_SUBJECT, htmlBody, Constants.EMAIL_CONTENT_TYPE);
            sendGridEmailService.sendEmail(emailInfo, mailProperties.getApiKey());
        } catch (IOException | TemplateException | EmailException e) {
            throw new EmailException("Email Processing Failed", e);
        }
        return "Email Sent!";
    }

    public Optional<City> getCityById(UUID cityId) throws CityNotFoundException {
        return Optional.of(this.cityRepository.findById(cityId))
                .orElseThrow(() -> new CityNotFoundException("City with id " + cityId + " not found"));
    }

    private Optional<Zone> getZoneBySymbol(String zoneSymbol, UUID cityId) {
        return this.zoneRepository.findBySymbol(zoneSymbol, cityId);
    }

    private Optional<ZoneLandUse> getZoneLandUseByDescription(String zoneLandUseDescription, UUID cityId) {
        return this.zoneLandUseRepository.findByZoneLandUseByDescription(zoneLandUseDescription, cityId);
    }

    private Optional<AllowedLandUse> getAllowedLandUseByZoneAndZoneLandUSe(UUID zoneId, UUID zoneLandUseId) {
        return this.allowedLandUseRepository.findAllByZoneandZoneLandUse(zoneId, zoneLandUseId);
    }

    public void createUseCase(UseCaseDto useCaseDto) throws CityNotFoundException, UseCaseNotFoundException {
        City city = new City(Optional.ofNullable(getCityById(useCaseDto.getCityId()))
                .orElseThrow(() -> new CityNotFoundException("City with id " + useCaseDto.getCityId() + " not found")));

        //Get zone by zone symbol
        Zone zone;
        Optional<Zone> zoneOptional = getZoneBySymbol(useCaseDto.getZoneSymbol(), city.getId());
        zone = zoneOptional.orElseGet(() -> new Zone(useCaseDto.getZoneSymbol(), useCaseDto.getZoneDescription(),
                city));
            zoneRepository.save(zone);

            //Construct DevelopmentStandards and save it to the database
            DevelopmentStandards developmentStandards = new DevelopmentStandards(useCaseDto.getGeneralStandardURL(),
                    useCaseDto.getAdditionalStandardURL(), useCaseDto.getGardenStandardURL(),
                    useCaseDto.getFrontageAndFacadesStandardURL(), zone);
            developmentStandardsRepository.save(developmentStandards);

            //Get zoneLandUseOptional by Description
            Optional<ZoneLandUse> zoneLandUseOptional = getZoneLandUseByDescription(useCaseDto.getZoneLandUseDescription(), city.getId());
            ZoneLandUse zoneLandUse;
            if (zoneLandUseOptional.isEmpty()) {
                //Construct ZoneLandUse and save it to the database
                zoneLandUse = new ZoneLandUse(useCaseDto.getZoneLandUseDescription(), city);
                zoneLandUseRepository.save(zoneLandUse);
            } else {
                zoneLandUse = zoneLandUseOptional.get();
            }

            //Get AllowedLandUse by zone and landUse
            Optional<AllowedLandUse> allowedLandUseOptional = getAllowedLandUseByZoneAndZoneLandUSe(zone.getId(), zoneLandUse.getId());
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
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Zone zone = zoneOptional.get();
            zone.setDescription(useCaseDto.getZoneDescription());
            zone.setZoneSymbol(useCaseDto.getZoneSymbol());
            zoneRepository.save(zone);

        //Construct DevelopmentStandards and save it to the database
        Optional<DevelopmentStandards> developmentStandardsOptional =
                Optional.of(developmentStandardsRepository.findByZoneId(useCaseDto.getZoneId()))
                .orElseThrow(() -> new UseCaseNotFoundException("Use Case Invalid: No Existing Development Standards " +
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
                Optional.of(allowedLandUseRepository.findAllByZoneandZoneLandUse(useCaseDto.getZoneId(), useCaseDto.getZoneLandUseId()))
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
}

