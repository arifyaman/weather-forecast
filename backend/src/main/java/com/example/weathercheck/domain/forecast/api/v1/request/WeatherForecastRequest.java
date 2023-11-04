package com.example.weathercheck.domain.forecast.api.v1.request;

import com.example.weathercheck.domain.forecast.model.sub.ForecastStatementType;

import java.time.LocalDate;
import java.util.UUID;

public record WeatherForecastRequest(
        LocalDate start,
        LocalDate end,
        ForecastStatementType type,
        UUID placeId
) {
}
