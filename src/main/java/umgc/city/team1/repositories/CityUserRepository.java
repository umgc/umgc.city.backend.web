package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.CityUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityUserRepository extends JpaRepository<CityUser, UUID> {
    List<CityUser> findByEmailAddress(String emailAddress);
    List<CityUser> findByFirstNameAndLastName(String firstName, String lastName);
}


