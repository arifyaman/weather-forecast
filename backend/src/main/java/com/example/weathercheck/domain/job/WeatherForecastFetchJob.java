package com.example.weathercheck.domain.job;

import com.example.weathercheck.client.impl.XMLWeatherClient;
import com.example.weathercheck.domain.forecast.service.WeatherForecastService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostConstruct
    void afterInit() {
        run();
    }

    @Scheduled(cron = "${app.job.weather-forecast-fetch.cron}")
    public void run() {
        try {
            weatherForecastService.writeData(client.getForecasts());
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
