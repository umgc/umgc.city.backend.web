package umgc.city.team1.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import umgc.city.team1.exceptions.*;
import umgc.city.team1.models.AllowedLandUse;
import umgc.city.team1.models.City;
import umgc.city.team1.models.CityUser;
import umgc.city.team1.models.Zone;
import umgc.city.team1.models.incoming.UseCaseDto;
import umgc.city.team1.models.incoming.UserAccount;
import umgc.city.team1.repositories.AllowedLandUseRepository;
import umgc.city.team1.repositories.CityRepository;
import umgc.city.team1.repositories.CityUserRepository;
import umgc.city.team1.repositories.ZoneRepository;
import umgc.city.team1.utilities.Constants;
import umgc.city.team1.utilities.SendGridEmailService;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Data
public class ZoningProjectService {

    @Value("${email.home-url}")
    private String emailURLLink;
    private final Logger logger = LoggerFactory.getLogger(ZoningProjectService.class);
    private final CityUserRepository cityUserRepository;
    private final CityRepository cityRepository;
    private final ZoneRepository zoneRepository;
    private final AllowedLandUseRepository allowedLandUseRepository;
    private final SendGridEmailService sendGridEmailService;
    private final Configuration freemarkerConfig;





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
        model.put("host", emailURLLink);

        try {
            Template t = freemarkerConfig.getTemplate("email-template.ftl");
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            sendGridEmailService.sendEmail(Constants.EMAIL_SENDER, cityUser.get(0).getEmailAddress(),
                    Constants.EMAIL_SUBJECT,
                    htmlBody);
        } catch (IOException | TemplateException e) {
            throw new EmailException("Email Processing Failed", e);
        }
        return "Email Sent!";
    }
}
