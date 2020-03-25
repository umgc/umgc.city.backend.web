package umgc.city.team1.contollers;

import com.sun.net.httpserver.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umgc.city.team1.models.incoming.UserAccount;
import umgc.city.team1.services.ZoningProjectService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/city-zoning-project-management")
public class ZoningProjectController {

private ZoningProjectService zoningProjectService;

public ZoningProjectController(ZoningProjectService zoningProjectService){
    this.zoningProjectService = zoningProjectService;
}

    @PostMapping(value = "/createUserAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUserAccount(@RequestBody UserAccount userAccount) {
    zoningProjectService.createUserAccount(userAccount);
    return ResponseEntity.ok("User Successfully Created");
    }

}
