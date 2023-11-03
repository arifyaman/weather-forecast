package com.example.weathercheck.domain.forecast.api.v1;

import com.example.weathercheck.domain.forecast.api.v1.response.WeatherForecastResponse;
import com.example.weathercheck.domain.forecast.service.WeatherForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weather-forecast")
@RequiredArgsConstructor
public class WeatherForecastController {
    private final WeatherForecastService weatherForecastService;

    @GetMapping
    public WeatherForecastResponse getForecast() {
        return weatherForecastService.getForecast();
    }

    @GetMapping("/places")
    public List<String> getPlaces() {
        return weatherForecastService.getPlaceNames();
    }
}
