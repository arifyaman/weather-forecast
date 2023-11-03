package com.example.weathercheck.domain.forecast.api.v1.response;

import com.example.weathercheck.domain.forecast.model.ForecastStatementType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record WeatherForecastStatementResponse(
        LocalDate date,
        ForecastStatementType type,
        String phenomenon,
        String statement,
        Integer minTemperature,
        Integer maxTemperature,
        PlaceForecastResponse placeForecast
) {
}
