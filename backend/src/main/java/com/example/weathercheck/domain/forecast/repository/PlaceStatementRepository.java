package com.example.weathercheck.domain.forecast.repository;

import com.example.weathercheck.domain.forecast.model.ForecastStatement;
import com.example.weathercheck.domain.forecast.model.Place;
import com.example.weathercheck.domain.forecast.model.PlaceStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaceStatementRepository extends JpaRepository<PlaceStatement, UUID> {
    Optional<PlaceStatement> findByForecastStatementAndPlace(ForecastStatement forecastStatement, Place place);

    @Query("SELECT ps from PlaceStatement ps " +
            "where ps.forecastStatement.forecast.date >= :before and ps.forecastStatement.forecast.date <= :after and ps.place.id = :placeId " +
            "order by ps.forecastStatement.forecast.date")
    List<PlaceStatement> findPlaceStatementForecasts(LocalDate before, LocalDate after, UUID placeId);
}
