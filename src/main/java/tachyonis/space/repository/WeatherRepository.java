package tachyonis.space.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import tachyonis.space.entity.Location;
import tachyonis.space.entity.Weather;

public interface WeatherRepository extends JpaRepository<Location, Long> {
    @Query(value = "SELECT loc FROM Location loc WHERE timezone = 'America/Los_Angeles' ")
    List<Location> findAll();
    List<Location> findAllBytimezone(String timezone);
}
