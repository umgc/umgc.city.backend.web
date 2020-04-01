package umgc.city.team1.contollers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umgc.city.team1.services.ZoningProjectService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/city-zoning-project-management")
public class ZoningProjectController {

private ZoningProjectService zoningProjectService;

public ZoningProjectController(ZoningProjectService zoningProjectService){
    this.zoningProjectService = zoningProjectService;

}
}
