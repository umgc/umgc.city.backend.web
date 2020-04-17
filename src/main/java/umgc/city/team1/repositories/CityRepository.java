package umgc.city.team1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umgc.city.team1.models.City;
import umgc.city.team1.models.incoming.UseCaseDto;

import java.util.List;
import java.util.UUID;

@Repository
    public interface CityRepository extends JpaRepository<City, UUID> {
    }


