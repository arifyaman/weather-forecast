package com.example.weathercheck.domain.forecast.api.v1.response;

public record PlaceForecastResponse(
        String name,
        String phenomenon,
        Integer minTemperature,
        Integer maxTemperature
) {
}
