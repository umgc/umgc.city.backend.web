package umgc.city.team1.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umgc.city.team1.exceptions.*;
import umgc.city.team1.models.AllowedLandUse;
import umgc.city.team1.models.CityUser;
import umgc.city.team1.models.Zone;
import umgc.city.team1.models.ZoneLandUse;
import umgc.city.team1.models.incoming.UseCaseDto;
import umgc.city.team1.models.incoming.UserAccount;
import umgc.city.team1.repositories.CityUserRepository;
import umgc.city.team1.repositories.ZoneLandUseRepository;
import umgc.city.team1.repositories.ZoneRepository;
import umgc.city.team1.services.ZoningProjectService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/city-zoning-project-management")
public class ZoningProjectController {

    private final Logger logger = LoggerFactory.getLogger(ZoningProjectController.class);
    private ZoningProjectService zoningProjectService;
    private ZoneLandUseRepository zoneLandUseRepository;
    private ZoneRepository zoneRepository;
    private CityUserRepository cityUserRepository;

    public ZoningProjectController(ZoningProjectService zoningProjectService,
                                   ZoneLandUseRepository zoneLandUseRepository,
                                   ZoneRepository zoneRepository,
                                   CityUserRepository cityUserRepository) {
        this.zoningProjectService = zoningProjectService;
        this.zoneLandUseRepository = zoneLandUseRepository;
        this.zoneRepository = zoneRepository;
        this.cityUserRepository = cityUserRepository;
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUserAccount(@RequestBody UserAccount userAccount) throws ObjectCreationFailedException {
        return ResponseEntity.ok( zoningProjectService.createUserAccount(userAccount));
    }

    @PostMapping(value = "/usecases", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUseCase(@RequestBody UseCaseDto useCase) throws CityNotFoundException, UseCaseNotFoundException {
        zoningProjectService.createUseCase(useCase);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/usecases", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> editUseCase(@RequestBody UseCaseDto useCase) throws UseCaseNotFoundException {
        zoningProjectService.editUseCase(useCase);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/cities/{id}/zones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Zone>> getZonesByCity(@PathVariable("id") UUID cityId) throws ZoneNotFoundException {
        return new ResponseEntity<>(zoningProjectService.getZonesByCityId(cityId), HttpStatus.FOUND);
    }

    @GetMapping(value = "/cities/zones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Zone> getZoneById(@PathVariable("id") UUID zoneId) throws ZoneNotFoundException {
        return new ResponseEntity<>(zoneRepository.getZoneById(zoneId), HttpStatus.FOUND);
    }

    @GetMapping(value = "/cities/zones/{id}/allowedlanduses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AllowedLandUse>> getAllowedLandUsesByZoneId(@PathVariable("id") UUID zoneId,
                                                                           Pageable pageable) throws AllowedLandUseNotFoundException {
        return new ResponseEntity<>(zoningProjectService.getAllowedLandUsesByZoneId(zoneId,
                pageable), HttpStatus.FOUND);
    }

    @GetMapping(value = "/cities/zones/{id}/usecases", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UseCaseDto>> getUseCasesByZoneId(@PathVariable("id") UUID zoneId) throws UseCaseNotFoundException {
        return new ResponseEntity<>(zoningProjectService.getUseCasesByZone(zoneId), HttpStatus.FOUND);
    }

    @GetMapping(value = "/cities/{id}/usecases", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UseCaseDto>> getUseCasesByCityId(@PathVariable("id") UUID cityId) throws UseCaseNotFoundException {
        return new ResponseEntity<>(zoningProjectService.getUseCasesByCity(cityId), HttpStatus.FOUND);
    }


    @PostMapping(value = "/users/sendcredentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendAccountCredentialEmail(@RequestBody UserAccount userAccount) throws EmailException {
        return ResponseEntity.ok(zoningProjectService.sendAdminUserCredentialsInEmail(userAccount));
    }

    @DeleteMapping(value = "/usecases/{id}")
    public ResponseEntity<HttpStatus> deleteUseCases(@PathVariable("id") UUID id) {
        try {
            zoneLandUseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
      Completed By:       Tarig
      Purpose:            Get use case by ID
      Class Name:         ZoningProjectController
      Method Name:        findUseCaseById
      Method Return Type: ResponseEntity
      Method Parameter:   String
      Exceptions:         ZoneLandUseNotFoundException or Exception
      Missing items:
          1. Who ever implemented FetchType.LAZY did not complete the session part
    */
    @GetMapping(value = "/usecases/{id}")
    public ResponseEntity<?> findUseCaseById(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            try {
                Optional<ZoneLandUse> zoneLandUse = zoneLandUseRepository.findById(uuid);
                return new ResponseEntity<>(zoneLandUse.orElseThrow(() -> new ZoneLandUseNotFoundException(uuid)), HttpStatus.OK);
            } catch (ZoneLandUseNotFoundException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid ID.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /*
        Completed By:       Tarig
        Purpose:            Update use case by ID
        Class Name:         ZoningProjectController
        Method Name:        updateUseCaseById
        Method Return Type: ResponseEntity
        Method Parameters:  String and ZoneLandUse
        Exceptions:         ZoneLandUseNotFoundException or Exception
        Missing items:
            1. Who ever implemented FetchType.LAZY did not complete the session part
    */
    @PutMapping(value = "/usecases/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUseCaseById(@PathVariable("id") String id,
                                               @RequestBody ZoneLandUse zoneLandUse) {
        try {
            UUID uuid = UUID.fromString(id);
            try {
                Optional<ZoneLandUse> currnetZoneLandUse = zoneLandUseRepository.findById(uuid);
                if (currnetZoneLandUse.isPresent()) {
                    currnetZoneLandUse.get().setDescription(zoneLandUse.getDescription());
                   // currnetZoneLandUse.get().setLandUseName(zoneLandUse.getLandUseName());
                    zoneLandUseRepository.saveAndFlush(currnetZoneLandUse.get());
                }
                return new ResponseEntity<ZoneLandUse>(currnetZoneLandUse.orElseThrow(() -> new ZoneLandUseNotFoundException(uuid)), HttpStatus.OK);
            } catch (ZoneLandUseNotFoundException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid ID.", HttpStatus.NOT_ACCEPTABLE);
        }
    }


    /*
        Completed By:       Tarig
        Purpose:            Send a conformation email to the city user
        Class Name:         ZoningProjectController
        Method Name:        sendEmail
        Method Return Type: void
        Method Parameter:   string
        Exception:          MessagingException
        Missing items:
            1. email address
    */
    void sendEmail(String emailAddress) throws MessagingException {
        Session session = setUpEmailService();

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("email@email.email", false));


        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
        msg.setSubject("UMGCCity Conformation");
        msg.setContent("UMGCCity Conformation", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Your UMGCCity account was created successfully.", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }

    /*
        Completed By:       Tarig
        Purpose:            email conformation to a city user
        Class Name:         ZoningProjectController
        Method Name:        emailConformation
        Method Return Type: ResponseEntity
        Method Parameters:  String
        Exceptions:         ZoneLandUseNotFoundException or Exception
        Missing items:      none
    */
    @RequestMapping(value = "/emailConformation/{id}")
    public ResponseEntity<?> emailConformation(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            try {
                CityUser cityUser = cityUserRepository.findById(uuid).orElseThrow(() -> new ZoneLandUseNotFoundException(uuid));
                try {
                    sendEmail(cityUser.getEmailAddress());
                } catch (MessagingException e) {
                    return new ResponseEntity<>("Unable to access email.", HttpStatus.EXPECTATION_FAILED);
                }
                return new ResponseEntity<>("Conformation was sent successfully.", HttpStatus.OK);
            } catch (ZoneLandUseNotFoundException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid ID.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /*
      Completed By:       Tarig
      Purpose:            email password to a city user
      Class Name:         ZoningProjectController
      Method Name:        emailPassword
      Method Return Type: ResponseEntity
      Method Parameters:  String
      Exceptions:         ZoneLandUseNotFoundException or Exception
      Missing items:      none
    */
    @RequestMapping(value = "/emailPassword/{id}")
    public ResponseEntity<?> emailPassword(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            try {
                CityUser cityUser = cityUserRepository.findById(uuid).orElseThrow(() -> new ZoneLandUseNotFoundException(uuid));
                try {
                    sendPassword(cityUser.getEmailAddress(),cityUser.getPassword());
                } catch (MessagingException e) {
                    return new ResponseEntity<>("Unable to access email.", HttpStatus.EXPECTATION_FAILED);
                }
                return new ResponseEntity<>("Password was sent successfully.", HttpStatus.OK);
            } catch (ZoneLandUseNotFoundException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid ID.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /*
      Completed By:       Tarig
      Purpose:            set up email service
      Class Name:         ZoningProjectController
      Method Name:        setUpEmailService
      Method Return Type: Session
      Method Parameters:  none
      Exceptions:         none
      Missing items:
           1. email address
           2. Password
           3. Was setup for Gmail
  */
    public Session setUpEmailService() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("email@email.email", "password");
            }
        });
        return session;
    }

    /*
       Completed By:       Tarig
       Purpose:            Send password to the city user
       Class Name:         ZoningProjectController
       Method Name:        sendPassword
       Method Return Type: void
       Method Parameter:   string and string
       Exception:          MessagingException
       Missing items:      none
   */
    void sendPassword(String emailAddress,String password) throws MessagingException {
        Session session = setUpEmailService();

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("email@email.email", false));


        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
        msg.setSubject("UMGCCity User's password");
        msg.setContent("UMGCCity User's password", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Your UMGCCity account password is " + password + ".", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
