package com.example.weathercheck.domain.forecast.api.v1.response;

import java.util.List;

public record WeatherForecastResponse(
        List<WeatherForecastStatementResponse> forecasts
) {
}
