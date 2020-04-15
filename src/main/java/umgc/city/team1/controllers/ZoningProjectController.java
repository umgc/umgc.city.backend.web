package umgc.city.team1.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umgc.city.team1.models.AllowedLandUse;
import umgc.city.team1.models.Zone;
import umgc.city.team1.models.incoming.UseCaseDto;
import umgc.city.team1.models.incoming.UserAccount;
import umgc.city.team1.repositories.ZoneLandUseRepository;
import umgc.city.team1.services.ZoningProjectService;


import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/city-zoning-project-management")
public class ZoningProjectController {

private ZoningProjectService zoningProjectService;
private ZoneLandUseRepository zoneLandUseRepository;

public ZoningProjectController(ZoningProjectService zoningProjectService, ZoneLandUseRepository zoneLandUseRepository){
    this.zoningProjectService = zoningProjectService;
    this.zoneLandUseRepository = zoneLandUseRepository;
}

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUserAccount(@RequestBody UserAccount userAccount) {
    zoningProjectService.createUserAccount(userAccount);
    return ResponseEntity.ok("User Successfully Created");
    }

    @GetMapping(value = "/cities/{id}/zones", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Zone> getZonesByCity(@PathVariable("id") UUID cityId){
        return this.zoningProjectService.getZonesByCityId(cityId);
    }

    @GetMapping(value = "/cities/zones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Zone getZoneById(@PathVariable("id") UUID zoneId){
        return this.zoningProjectService.getZonesById(zoneId);
    }

    @GetMapping(value = "/cities/zones/{id}/allowedlanduses", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<AllowedLandUse> getAllowedLandUsesByZoneId(@PathVariable("id") UUID zoneId, Pageable pageable){
        return this.zoningProjectService.getAllowedLandUsesByZoneId(zoneId, pageable);
    }

    @GetMapping(value = "/cities/zones/{id}/usecases", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UseCaseDto> getUseCasesByZoneId(@PathVariable("id") UUID zoneId){
    return this.zoningProjectService.getUseCaseDto(zoneId);
    }

    @DeleteMapping(value = "/usecases/{id}")
    public ResponseEntity<HttpStatus> deleteUseCases(@PathVariable("id") UUID id) {
        try {
            zoneLandUseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


//    @PutMapping(value = "/usecase/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
//    MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity editUseCase(@RequestBody UseCase useCase) {
//        zoningProjectService.editUseCase(useCase);
//        return ResponseEntity.ok("Use Case Successfully Edited");
//    }

//    @PostMapping(value = "/usecases", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
//            MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity createUseCase(@RequestBody UseCase useCase) {
//        zoningProjectService.createUseCase(useCase);
//        return ResponseEntity.ok("Use Case Successfully Created");
//    }

//    @GetMapping(value = "/cities/{id}/usecases", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity getUseCasesByCity(@PathVariable("id") UUID id) {
//        return ResponseEntity.ok(zoningProjectService.getUseCaseByCity(id));
//    }
//
//
//    @GetMapping(value = "/users/emailaddress", produces =
//            MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity getUserEmailAddress(@RequestBody UserAccount userAccount) {
//        return ResponseEntity.ok(zoningProjectService.getUserEmailAddress(userAccount));
//    }

//    @GetMapping(value = "/email")
//    public ResponseEntity sendUserCredentialsEmail(@RequestBody UserAccount userAccount) {
//       zoningProjectService.sendUserCrendialsEmail(userAccount);
//        return ResponseEntity.ok("Email sent");
//    }
//
}
