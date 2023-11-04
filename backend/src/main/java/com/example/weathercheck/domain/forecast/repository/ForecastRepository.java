package com.example.weathercheck.domain.forecast.repository;

import com.example.weathercheck.domain.forecast.model.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, UUID> {
    Optional<Forecast> findByDate(LocalDate date);
}
