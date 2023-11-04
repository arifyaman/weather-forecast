package com.example.weathercheck.domain.forecast.api.v1;

import com.example.weathercheck.domain.forecast.api.v1.request.WeatherForecastRequest;
import com.example.weathercheck.domain.forecast.api.v1.response.PlaceResponse;
import com.example.weathercheck.domain.forecast.api.v1.response.WeatherForecastResponse;
import com.example.weathercheck.domain.forecast.service.WeatherForecastService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherForecastControllerTest {

    @Mock
    private WeatherForecastService weatherForecastService;

    @InjectMocks
    private WeatherForecastController weatherForecastController;

    @Test
    void testGetForecast() {
        WeatherForecastRequest request = new WeatherForecastRequest(null, null, null, null);
        WeatherForecastResponse expectedResponse = new WeatherForecastResponse(List.of());
        when(weatherForecastService.getForecast(any())).thenReturn(expectedResponse);

        WeatherForecastResponse response = weatherForecastController.getForecast(request);

        assertNotNull(response);
    }

    @Test
    void testGetPlaces() {
        List<PlaceResponse> expectedResponses = List.of();
        when(weatherForecastService.getPlaceNames()).thenReturn(expectedResponses);

        List<PlaceResponse> responses = weatherForecastController.getPlaces();

        assertNotNull(responses);
    }
}