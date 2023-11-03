package com.example.weathercheck.domain.forecast.api.v1.request;

import com.example.weathercheck.domain.forecast.model.ForecastStatementType;

import java.time.LocalDate;

public record WeatherForecastRequest(
        LocalDate start,
        LocalDate end,
        ForecastStatementType type,
        String place
) {
}
