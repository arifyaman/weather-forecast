package com.example.weathercheck.domain.job;

import com.example.weathercheck.client.impl.XMLWeatherClient;
import com.example.weathercheck.domain.forecast.service.WeatherForecastService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

@Component
@Slf4j
@RequiredArgsConstructor
public class WeatherForecastFetchJob {
    private final XMLWeatherClient client;
    private final WeatherForecastService weatherForecastService;

    @Value("${app.job.weather-forecast-fetch.disabled:false}")
    private boolean disabled;

    @PostConstruct
    void afterInit() {
        if (!disabled)
            run();
    }

    @Scheduled(cron = "${app.job.weather-forecast-fetch.cron}")
    public void run() {
        if (disabled) return;

        try {
            weatherForecastService.writeData(client.getForecasts());
            log.info("Forecast data has been updated.");
        } catch (URISyntaxException e) {
            log.error("URI issue", e);
        } catch (IOException e) {
            log.error("IOException issue", e);
        } catch (InterruptedException e) {
            log.error("InterruptedException issue", e);
            Thread.currentThread().interrupt();
        } catch (JAXBException e) {
            log.error("JAXBException issue", e);
        }
    }
}
