package com.example.weathercheck.domain.forecast.api.v1.response;

import java.time.LocalDate;
import java.util.List;

public record WeatherForecastResponse(
        List<GroupedForecast> forecasts
) {
    public record GroupedForecast(
            LocalDate date,
            List<WeatherForecastStatementResponse> details
    ){

    }
}
