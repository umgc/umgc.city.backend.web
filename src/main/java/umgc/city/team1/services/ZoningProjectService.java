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

    public List<UseCaseDto> getUseCaseDto(UUID zoneId) throws UseCaseNotFoundException {
        return Optional.of(zoneRepository.findUseCaseByZoneId(zoneId)).orElseThrow(() -> new UseCaseNotFoundException(
                "Land Use Cases could not be found for zone with Id: " + zoneId));
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

    public void createUseCase(UseCaseDto useCaseDto) throws CityNotFoundException, UseCaseBadRequestException {
        //null out id fields
        useCaseDto.setZoneId(null);
        useCaseDto.setZoneLandUseId(null);

        //validate data
        validateCreateUseCase(useCaseDto);

        //Get city by city Id
        Optional<City> cityOptional = getCityById(useCaseDto.getCityId());

        if (!cityOptional.isPresent()) throw new CityNotFoundException("City with id " + useCaseDto.getCityId() + " not found");

        City city = cityOptional.get();

        //Get zone by zone symbol
        Optional<Zone> zoneOptional = getZoneBySymbol(useCaseDto.getZoneSymbol(), city.getId());

        Zone zone = new Zone();
        if (!zoneOptional.isPresent()) {
            //Construct Zone and save it to the database
            zone.setCity(city);
            zone.setDescription(useCaseDto.getZoneDescription());
            zone.setZoneSymbol(useCaseDto.getZoneSymbol());
            zoneRepository.save(zone);

            //Construct DevelopmentStandards and save it to the database
            DevelopmentStandards developmentStandards = new DevelopmentStandards();
            developmentStandards.setZone(zone);
            developmentStandards.setAdditionalStandardsURL(useCaseDto.getAdditionalStandardURL());
            developmentStandards.setFrontageAndFacadesStandardsURL(useCaseDto.getFrontageAndFacadesStandardURL());
            developmentStandards.setGardenStandardsURL(useCaseDto.getGardenStandardURL());
            developmentStandards.setGeneralStandardsURL(useCaseDto.getGeneralStandardURL());
            developmentStandardsRepository.save(developmentStandards);
        } else {
            zone = zoneOptional.get();
        }

        //Get zoneLandUseOptional by Description
        Optional<ZoneLandUse> zoneLandUseOptional = getZoneLandUseByDescription(useCaseDto.getZoneLandUseDescription(), city.getId());

        ZoneLandUse zoneLandUse = new ZoneLandUse();
        if (!zoneLandUseOptional.isPresent()) {
            //Construct ZoneLandUse and save it to the database
            zoneLandUse.setCity(city);
            zoneLandUse.setDescription(useCaseDto.getZoneLandUseDescription());
            zoneLandUseRepository.save(zoneLandUse);
        } else {
            zoneLandUse = zoneLandUseOptional.get();
        }

        //Get AllowedLandUse by zone and landUse
        Optional<AllowedLandUse> allowedLandUseOptional = getAllowedLandUseByZoneAndZoneLandUSe(zone.getId(), zoneLandUse.getId());

        AllowedLandUse allowedLandUse = new AllowedLandUse();
        if (!allowedLandUseOptional.isPresent()) {
            //Construct AllowedLandUse and save it to the database
            allowedLandUse.setZoneLandUse(zoneLandUse);
            allowedLandUse.setZone(zone);
            allowedLandUse.setApplicationUrl(useCaseDto.getApplicationURL());
            allowedLandUse.setPermitDescription(useCaseDto.getPermitDescription());
            allowedLandUse.setPermitName(useCaseDto.getPermitName());
            allowedLandUse.setProcedureUrl(useCaseDto.getProcedureURL());
            allowedLandUseRepository.save(allowedLandUse);
        } else {
            throw new UseCaseBadRequestException("Use Case Already Exists");
        }
    }

    public void editUseCase(UseCaseDto useCaseDto) throws ZoneNotFoundException, UseCaseBadRequestException {
        //validate data
        validateEditUseCase(useCaseDto);

        //Get zone by id
        Optional<Zone> zoneOptional = zoneRepository.findById(useCaseDto.getZoneId());

        if (zoneOptional.isPresent()) {
            Zone zone = zoneOptional.get();
            //Construct Zone and save it to the database
            zone.setDescription(useCaseDto.getZoneDescription());
            zone.setZoneSymbol(useCaseDto.getZoneSymbol());
            zoneRepository.save(zone);

            //Construct DevelopmentStandards and save it to the database
            Optional<DevelopmentStandards> developmentStandardsOptional = developmentStandardsRepository.findByZoneId(zone.getId());

            if (developmentStandardsOptional.isPresent()) {
                DevelopmentStandards developmentStandards = new DevelopmentStandards();
                developmentStandards = developmentStandardsOptional.get();
                developmentStandards.setAdditionalStandardsURL(useCaseDto.getAdditionalStandardURL());
                developmentStandards.setFrontageAndFacadesStandardsURL(useCaseDto.getFrontageAndFacadesStandardURL());
                developmentStandards.setGardenStandardsURL(useCaseDto.getGardenStandardURL());
                developmentStandards.setGeneralStandardsURL(useCaseDto.getGeneralStandardURL());
                developmentStandardsRepository.save(developmentStandards);
            }

            //Get zoneLandUseOptional by Description
            Optional<ZoneLandUse> zoneLandUseOptional = zoneLandUseRepository.findById(useCaseDto.getZoneLandUseId());

            ZoneLandUse zoneLandUse = new ZoneLandUse();
            if (zoneLandUseOptional.isPresent()) {
                //Construct ZoneLandUse and save it to the database
                zoneLandUse = zoneLandUseOptional.get();
                zoneLandUse.setDescription(useCaseDto.getZoneLandUseDescription());
                zoneLandUseRepository.save(zoneLandUse);
            }

            //Get AllowedLandUse by zone and landUse
            Optional<AllowedLandUse> allowedLandUseOptional = getAllowedLandUseByZoneAndZoneLandUSe(zone.getId(), zoneLandUse.getId());

            AllowedLandUse allowedLandUse = new AllowedLandUse();
            if (allowedLandUseOptional.isPresent()) {
                //Construct AllowedLandUse and save it to the database
                allowedLandUse = allowedLandUseOptional.get();
                allowedLandUse.setApplicationUrl(useCaseDto.getApplicationURL());
                allowedLandUse.setPermitDescription(useCaseDto.getPermitDescription());
                allowedLandUse.setPermitName(useCaseDto.getPermitName());
                allowedLandUse.setProcedureUrl(useCaseDto.getProcedureURL());
                allowedLandUseRepository.save(allowedLandUse);
            } else {
                throw new UseCaseBadRequestException("Use case does not exist");
            }
        } else {
            throw new ZoneNotFoundException("Zone with id " + useCaseDto.getZoneId() + " not found");
        }
    }

    private void validateEditUseCase(UseCaseDto useCaseDto) throws UseCaseBadRequestException {
        //validate data
        ArrayList<String> errors = new ArrayList<String>();

        if (useCaseDto.getZoneId() == null) errors.add("zoneId is required");
        if (useCaseDto.getZoneLandUseId() == null) errors.add("zoneLandUseId is required");
        if (useCaseDto.getCityId() == null) errors.add("cityId is required");
        if (useCaseDto.getZoneSymbol() == null) errors.add("zoneSymbol is required");
        if (useCaseDto.getZoneDescription() == null) errors.add("zoneDescription is required");
        if (useCaseDto.getApplicationURL() == null) errors.add("applicationURL is required");
        if (useCaseDto.getProcedureURL() == null) errors.add("procedureURL is required");
        if (useCaseDto.getPermitName() == null) errors.add("permitName is required");
        if (useCaseDto.getPermitDescription() == null) errors.add("permitDescription is required");
        if (useCaseDto.getZoneLandUseDescription() == null) errors.add("zoneLandUseDescription is required");
        if (useCaseDto.getGeneralStandardURL() == null) errors.add("generalStandardURL is required");
        if (useCaseDto.getGardenStandardURL() == null) errors.add("gardenStandardURL is required");
        if (useCaseDto.getAdditionalStandardURL() == null) errors.add("additionalStandardURL is required");
        if (useCaseDto.getFrontageAndFacadesStandardURL() == null)
            errors.add("frontageAndFacadesStandardURL is required");

        //throw exception if there is valid data
        if (errors.size() > 0) throw new UseCaseBadRequestException(String.join(", ", errors));
    }

    private void validateCreateUseCase(UseCaseDto useCaseDto) throws UseCaseBadRequestException {
        //validate data
        ArrayList<String> errors = new ArrayList<String>();

        if (useCaseDto.getCityId() == null) errors.add("cityId is required");
        if (useCaseDto.getZoneSymbol() == null) errors.add("zoneSymbol is required");
        if (useCaseDto.getZoneDescription() == null) errors.add("zoneDescription is required");
        if (useCaseDto.getApplicationURL() == null) errors.add("applicationURL is required");
        if (useCaseDto.getProcedureURL() == null) errors.add("procedureURL is required");
        if (useCaseDto.getPermitName() == null) errors.add("permitName is required");
        if (useCaseDto.getPermitDescription() == null) errors.add("permitDescription is required");
        if (useCaseDto.getZoneLandUseDescription() == null) errors.add("zoneLandUseDescription is required");
        if (useCaseDto.getGeneralStandardURL() == null) errors.add("generalStandardURL is required");
        if (useCaseDto.getGardenStandardURL() == null) errors.add("gardenStandardURL is required");
        if (useCaseDto.getAdditionalStandardURL() == null) errors.add("additionalStandardURL is required");
        if (useCaseDto.getFrontageAndFacadesStandardURL() == null)
            errors.add("frontageAndFacadesStandardURL is required");

        //throw exception if there is valid data
        if (errors.size() > 0) throw new UseCaseBadRequestException(String.join(", ", errors));
    }
}
