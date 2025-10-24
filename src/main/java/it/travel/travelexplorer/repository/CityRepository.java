package it.travel.travelexplorer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.travel.travelexplorer.domain.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    List<City> findAllByDeleteDateIsNull();

    Optional<City> findByUuidAndDeleteDateIsNull(UUID uuid);

    @Query("SELECT c FROM City c " +
            "WHERE c.name = :cityName " +
            "AND c.region.name = :regionName " +
            "AND c.country.name = :countryName " +
            "AND c.deleteDate IS NULL")
    Optional<City> findByNameAndRegionAndCountry(
            @Param("cityName") String cityName,
            @Param("regionName") String regionName,
            @Param("countryName") String countryName);
}
