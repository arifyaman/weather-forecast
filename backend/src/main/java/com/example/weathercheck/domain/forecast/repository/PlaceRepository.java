package com.example.weathercheck.domain.forecast.repository;

import com.example.weathercheck.domain.forecast.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID> {
    Optional<Place> findByName(String name);
}
