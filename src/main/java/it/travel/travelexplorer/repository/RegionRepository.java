package it.travel.travelexplorer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.travel.travelexplorer.domain.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {
    List<Region> findAllByDeleteDateIsNull();

    Optional<Region> findByUuidAndDeleteDateIsNull(UUID uuid);

    Optional<Region> findByNameAndDeleteDateIsNull(String name);
}
