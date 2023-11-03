package com.example.weathercheck.domain.forecast.repository;

import com.example.weathercheck.domain.forecast.model.ForecastStatement;
import com.example.weathercheck.domain.forecast.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID> {
    Optional<Place> findByForecastStatementAndName(ForecastStatement forecastStatement, String name);

    @Query("SELECT distinct p.name from Place p order by p.name")
    List<String> findDistinctNames();

    @Query("SELECT p from Place p where p.forecastStatement.forecast.date = :date and p.name = :name")
    List<Place> findPlacesForecast(LocalDate date, String name);
}
