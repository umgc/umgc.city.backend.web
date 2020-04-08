package umgc.city.team1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umgc.city.team1.models.incoming.UseCase;
import umgc.city.team1.models.incoming.UserAccount;
import umgc.city.team1.repositories.ZoneLandUseRepository;
import umgc.city.team1.services.ZoningProjectService;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/city-zoning-project-management")
public class ZoningProjectController {

<<<<<<< Updated upstream
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

    @PostMapping(value = "/usecases", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUseCase(@RequestBody UseCase useCase) {
        zoningProjectService.createUseCase(useCase);
        return ResponseEntity.ok("Use Case Successfully Created");
    }

//    @PostMapping(value = "/cities/{id}/usecases/upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
//            "text/csv")
//    public ResponseEntity handleCaseFileUpload(@RequestParam("file") MultipartFile file,
//                                               @PathVariable("id") UUID cityId) {
//        zoningProjectService.handleCaseFileUpload(file, cityId);
//        return ResponseEntity.ok("Data File Successfully Processed");
//    }

    @DeleteMapping(value = "/usecases/{id}")
    public ResponseEntity<HttpStatus> deleteUseCases(@PathVariable("id") UUID id) {
        try {
            zoneLandUseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
        // "Password must have 6 characters and 1 number.
    private Boolean passwordCheck(String password) {
        return password.length() >= 7 && hasOneDigit(password) && hasNCharacter(password)? true:false;
    }

    // Password has on digit
    private boolean hasOneDigit(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    // Password has n characters
    private boolean hasNCharacter(String password) {
        int charactersCount = 6;
        int count = 0;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                count++;
            }
        }
        return (count >= charactersCount) ? true:false;
    }




     */

//    @PutMapping(value = "/usecase/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
//    MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity editUseCase(@RequestBody UseCase useCase) {
//        zoningProjectService.editUseCase(useCase);
//        return ResponseEntity.ok("Use Case Successfully Edited");
//    }

//    @GetMapping(value = "/cities/{id}/usecases", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity getUseCasesByCity(@PathVariable("id") UUID id) {
//        return ResponseEntity.ok(zoningProjectService.getUseCaseByCity(id));
//    }
//
//    @GetMapping(value = "/cities/{city_id}/usecases/{case_id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity getUseCasesByCityAndCaseId(@PathVariable("city_id") UUID cityId,
//                                                     @PathVariable("case_id") UUID caseId) {
//        return ResponseEntity.ok(zoningProjectService.getUseCaseByCityandCaseId(cityId, caseId));
//    }
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
//    @GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
//            public ResponseEntity getAuthorization(@RequestBody UserAccount userAccount) {
//            return ResponseEntity.ok(zoningProjectService.getAuthorization(userAccount));
//            }
=======
    private ZoningProjectService zoningProjectService;

    public ZoningProjectController(ZoningProjectService zoningProjectService) {
        this.zoningProjectService = zoningProjectService;

    }
>>>>>>> Stashed changes
}
