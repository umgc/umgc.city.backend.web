package umgc.city.team1.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import umgc.city.team1.models.City;
import umgc.city.team1.models.CityUser;
import umgc.city.team1.models.incoming.UserAccount;
import umgc.city.team1.repositories.CityRepository;
import umgc.city.team1.repositories.CityUserRepository;

@Service
@AllArgsConstructor
public class ZoningProjectService {
    private final Logger logger = LoggerFactory.getLogger(ZoningProjectService.class);
    private final CityUserRepository cityUserRepository;
    private final CityRepository cityRepository;

    public void createUserAccount(UserAccount userAccount){
        cityRepository.save(new City(userAccount.getCity(), userAccount.getState(),cityUserRepository.save(new CityUser(userAccount.getFirstName(), userAccount.getLastName(),
                userAccount.getEmail(), userAccount.getPassword()))));
    }
}
