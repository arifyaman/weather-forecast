package com.example.weathercheck.domain.forecast.repository;

import com.example.weathercheck.domain.forecast.model.Forecast;
import com.example.weathercheck.domain.forecast.model.ForecastStatement;
import com.example.weathercheck.domain.forecast.model.ForecastStatementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ForecastStatementRepository extends JpaRepository<ForecastStatement, UUID> {
    Optional<ForecastStatement> findByForecastAndType(Forecast forecast, ForecastStatementType type);

    List<ForecastStatement> findByForecastDateAfterAndForecastDateBeforeOrderByForecastDate(LocalDate after, LocalDate before);
}
