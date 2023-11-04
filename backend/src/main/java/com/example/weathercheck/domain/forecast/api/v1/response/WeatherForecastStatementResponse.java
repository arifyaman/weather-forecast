package com.example.weathercheck.domain.forecast.api.v1.response;

import com.example.weathercheck.domain.forecast.model.sub.ForecastStatementType;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record WeatherForecastStatementResponse(
        ForecastStatementType type,
        String phenomenon,
        String statement,
        Integer minTemperature,
        Integer maxTemperature,
        PlaceForecastResponse placeForecast
) {
}
