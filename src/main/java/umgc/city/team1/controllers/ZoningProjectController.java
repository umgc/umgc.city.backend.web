package umgc.city.team1.controllers;

import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umgc.city.team1.exceptions.*;
import umgc.city.team1.models.Zone;
import umgc.city.team1.models.incoming.MapShape;
import umgc.city.team1.models.incoming.UseCaseDto;
import umgc.city.team1.models.incoming.UserAccount;
import umgc.city.team1.models.outgoing.MapCase;
import umgc.city.team1.repositories.ZoneLandUseRepository;
import umgc.city.team1.repositories.ZoneRepository;
import umgc.city.team1.services.ZoningProjectService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/city-zoning-project-management")
public class ZoningProjectController {

    private final Logger logger = LoggerFactory.getLogger(ZoningProjectController.class);
    private ZoningProjectService zoningProjectService;
    private ZoneLandUseRepository zoneLandUseRepository;
    private ZoneRepository zoneRepository;


    public ZoningProjectController(ZoningProjectService zoningProjectService,
                                   ZoneLandUseRepository zoneLandUseRepository,
                                   ZoneRepository zoneRepository) {
        this.zoningProjectService = zoningProjectService;
        this.zoneLandUseRepository = zoneLandUseRepository;
        this.zoneRepository = zoneRepository;
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUserAccount(@RequestBody UserAccount userAccount) throws ObjectCreationFailedException {
        zoningProjectService.createUserAccount(userAccount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/usecases", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUseCase(@RequestBody UseCaseDto useCase) throws CityNotFoundException, UseCaseNotFoundException {
        zoningProjectService.createUseCase(useCase);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/usecases", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> editUseCase(@RequestBody UseCaseDto useCase) throws UseCaseNotFoundException {
        zoningProjectService.editUseCase(useCase);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/cities/{id}/zones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Zone>> getZonesByCity(@PathVariable("id") UUID cityId) throws ZoneNotFoundException {
        return new ResponseEntity<>(zoningProjectService.getZonesByCityId(cityId), HttpStatus.OK);
    }

    @GetMapping(value = "/cities/zones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Zone> getZoneById(@PathVariable("id") UUID zoneId) {
        return new ResponseEntity<>(zoneRepository.getZoneById(zoneId), HttpStatus.OK);
    }

    @GetMapping(value = "/cities/zones/{id}/usecases", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UseCaseDto>> getUseCasesByZoneId(@PathVariable("id") UUID zoneId) throws UseCaseNotFoundException {
        return new ResponseEntity<>(zoningProjectService.getUseCasesByZone(zoneId), HttpStatus.OK);
    }

    @GetMapping(value = "/cities/{id}/usecases", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UseCaseDto>> getUseCasesByCityId(@PathVariable("id") UUID cityId) throws UseCaseNotFoundException {
        return new ResponseEntity<>(zoningProjectService.getUseCasesByCity(cityId), HttpStatus.OK);
    }

    @PostMapping(value = "/pasadena/zones", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MapCase> getPasadenaZoneData(@RequestBody MapShape mapShape) throws ZoneNotFoundException {
        return new ResponseEntity<>(zoningProjectService.getPasadenaZoneData(mapShape), HttpStatus.OK);
    }


    @PostMapping(value = "/users/sendCredentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> sendAccountCredentialEmail(@RequestBody UserAccount userAccount) throws
            IOException, TemplateException {
        zoningProjectService.sendAdminUserCredentialsInEmail(userAccount);
        return new ResponseEntity<>(HttpStatus.OK);
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
}
