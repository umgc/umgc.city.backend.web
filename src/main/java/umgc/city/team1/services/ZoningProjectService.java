package umgc.city.team1.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umgc.city.team1.exceptions.ZoneNotFoundException;
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
    private final AllowedLandUseRepository allowedLandUseRepository;

    public void createUserAccount(UserAccount userAccount){

        cityRepository.save(new City(userAccount.getCity(), userAccount.getState(),cityUserRepository.save(new CityUser(userAccount.getFirstName(), userAccount.getLastName(),
                userAccount.getEmail(), userAccount.getPassword(), userAccount.getAuthoritiesId() ))));
    }

    public List<Zone> getZonesByCityId(UUID cityId) {
        List<Zone> zones = this.zoneRepository.findAllByCity(cityId);
        if(zones.isEmpty())
            throw new ZoneNotFoundException("Zones were not found with city id", cityId);
        return zones;

    }

    public Page<AllowedLandUse> getAllowedLandUsesByZoneId(UUID zoneId, Pageable pageable){
        Page<AllowedLandUse> allowedLandUses = this.allowedLandUseRepository.findByZoneId(zoneId, pageable);
        if(allowedLandUses.isEmpty())
            throw new ZoneNotFoundException("Zones could not be found for city with Id: ", zoneId);
        return allowedLandUses;
    }

    public Zone getZonesById(UUID zoneId) {
        return Optional.of(this.zoneRepository.getOne(zoneId))
                .orElseThrow(() -> new ZoneNotFoundException(zoneId));
    }

    public List<UseCaseDto> getUseCaseDto(UUID zoneId){
        List<UseCaseDto> useCases = zoneRepository.findUseCaseByZoneId(zoneId);
        if(useCases.isEmpty())
            throw new ZoneNotFoundException("Land Use Cases could not be found for zone with Id: ", zoneId);
        return useCases;
    }
}
