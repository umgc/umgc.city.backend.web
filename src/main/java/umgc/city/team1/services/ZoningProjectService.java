package umgc.city.team1.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import umgc.city.team1.exceptions.ZoneNotFoundException;
import umgc.city.team1.models.City;
import umgc.city.team1.models.CityUser;
import umgc.city.team1.models.Zone;
import umgc.city.team1.models.incoming.UseCase;
import umgc.city.team1.models.incoming.UserAccount;
import umgc.city.team1.repositories.CityRepository;
import umgc.city.team1.repositories.CityUserRepository;
import umgc.city.team1.repositories.ZoneRepository;
import java.util.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ZoningProjectService {
    private final Logger logger = LoggerFactory.getLogger(ZoningProjectService.class);
    private final CityUserRepository cityUserRepository;
    private final CityRepository cityRepository;
    private final ZoneRepository zoneRepository;

    public void createUserAccount(UserAccount userAccount){
        cityRepository.save(new City(userAccount.getCity(), userAccount.getState(),cityUserRepository.save(new CityUser(userAccount.getFirstName(), userAccount.getLastName(),
                userAccount.getEmail(), userAccount.getPassword()))));
    }

    public void createUseCase(UseCase useCase){

    }

    public List<Zone> getZonesByCityId(UUID cityId) {
        List<Zone> zones = this.zoneRepository.findAllByCity(cityId);
        if(zones.isEmpty())
            throw new ZoneNotFoundException("Zones were not found with city id", cityId);
        return zones;

    }


    public Zone getZonesById(UUID zoneId) {
        return Optional.of(this.zoneRepository.getOne(zoneId))
                .orElseThrow(() -> new ZoneNotFoundException(zoneId));
    }
}
