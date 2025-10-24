package it.travel.travelexplorer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.travel.travelexplorer.domain.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {
    List<Country> findAllByDeleteDateIsNull();

    Optional<Country> findByUuidAndDeleteDateIsNull(UUID uuid);

    Optional<Country> findByNameAndDeleteDateIsNull(String name);
}
