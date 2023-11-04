package com.example.weathercheck.domain.forecast.service;

import com.example.weathercheck.domain.forecast.api.v1.request.WeatherForecastRequest;
import com.example.weathercheck.domain.forecast.api.v1.response.PlaceResponse;
import com.example.weathercheck.domain.forecast.api.v1.response.WeatherForecastResponse;
import com.example.weathercheck.domain.forecast.repository.ForecastRepository;
import com.example.weathercheck.domain.forecast.repository.ForecastStatementRepository;
import com.example.weathercheck.domain.forecast.repository.PlaceRepository;
import com.example.weathercheck.domain.forecast.repository.PlaceStatementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherForecastServiceTest {

    @Mock
    private ForecastRepository forecastRepository;

    @Mock
    private ForecastStatementRepository forecastStatementRepository;

    @Mock
    private PlaceStatementRepository placeStatementRepository;

    @Mock
    private PlaceRepository placeRepository;

    @InjectMocks
    private WeatherForecastService weatherForecastService;

    @Test
    void testGetForecastWithoutPlaceId() {
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(4);
        WeatherForecastRequest request = new WeatherForecastRequest(start, end, null, null);
        when(forecastStatementRepository.findForecastStatementForecasts(start, end)).thenReturn(List.of());

        WeatherForecastResponse response = weatherForecastService.getForecast(request);

        assertNotNull(response);
    }

    @Test
    void testGetForecastWithPlaceId() {
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(4);
        UUID placeId = UUID.randomUUID();
        WeatherForecastRequest request = new WeatherForecastRequest(start, end, null, placeId);
        when(placeStatementRepository.findPlaceStatementForecasts(start, end, placeId)).thenReturn(List.of());

        WeatherForecastResponse response = weatherForecastService.getForecast(request);

        assertNotNull(response);
    }

    @Test
    void testGetPlaceNames() {
        when(placeRepository.findAll()).thenReturn(List.of());

        List<PlaceResponse> placeResponses = weatherForecastService.getPlaceNames();

        assertNotNull(placeResponses);
    }
}
