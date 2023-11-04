package com.example.weathercheck.domain.job;

import com.example.weathercheck.client.impl.XMLWeatherClient;
import com.example.weathercheck.domain.forecast.service.WeatherForecastService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherForecastFetchJobTest {
    @Mock
    private XMLWeatherClient client;

    @Mock
    private WeatherForecastService weatherForecastService;

    @InjectMocks
    private WeatherForecastFetchJob weatherForecastFetchJob;

    @Test
    void testRun() throws URISyntaxException, IOException, InterruptedException, javax.xml.bind.JAXBException {
        when(client.getForecasts()).thenReturn(null);

        weatherForecastFetchJob.run();

        verify(weatherForecastService, times(1)).writeData(any());
    }

    @Test
    void testRunWithException() throws URISyntaxException, IOException, InterruptedException, javax.xml.bind.JAXBException {
        doThrow(URISyntaxException.class).when(client).getForecasts();

        weatherForecastFetchJob.run();

        verify(weatherForecastService, never()).writeData(any());
    }
}