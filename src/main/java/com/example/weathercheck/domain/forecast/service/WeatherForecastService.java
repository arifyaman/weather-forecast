package com.example.weathercheck.domain.forecast.service;

import com.example.weathercheck.client.impl.model.XMLForecast;
import com.example.weathercheck.client.impl.model.XMLForecasts;
import com.example.weathercheck.client.impl.model.XMLPlace;
import com.example.weathercheck.client.impl.model.XMLStatement;
import com.example.weathercheck.domain.forecast.api.v1.response.WeatherForecastResponse;
import com.example.weathercheck.domain.forecast.api.v1.response.WeatherForecastStatementResponse;
import com.example.weathercheck.domain.forecast.model.Forecast;
import com.example.weathercheck.domain.forecast.model.ForecastStatement;
import com.example.weathercheck.domain.forecast.model.ForecastStatementType;
import com.example.weathercheck.domain.forecast.model.Place;
import com.example.weathercheck.domain.forecast.repository.ForecastRepository;
import com.example.weathercheck.domain.forecast.repository.ForecastStatementRepository;
import com.example.weathercheck.domain.forecast.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherForecastService {
    private final ForecastRepository forecastRepository;
    private final ForecastStatementRepository forecastStatementRepository;
    private final PlaceRepository placeRepository;

    public WeatherForecastResponse getForecast() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate after4Days = LocalDate.now().plusDays(4);

        List<ForecastStatement> statements = forecastStatementRepository.findByForecastDateAfterAndForecastDateBeforeOrderByForecastDate(tomorrow, after4Days);
        return toResponse(statements);
    }

    public List<String> getPlaceNames() {
        return placeRepository.findDistinctNames();
    }

    private WeatherForecastResponse toResponse(List<ForecastStatement> statements) {
        return new WeatherForecastResponse(
                statements.stream().map(s -> new WeatherForecastStatementResponse(
                        s.getForecast().getDate(),
                        s.getType(),
                        s.getPhenomenon(),
                        s.getStatement(),
                        s.getMinTemperature(),
                        s.getMaxTemperature(),
                        null
                )).toList()
        );
    }

    public void writeData(XMLForecasts forecasts) {
        for (XMLForecast xmlForecast : forecasts.getForecasts()) {
            LocalDate date = LocalDate.parse(xmlForecast.getDate());

            Forecast.ForecastBuilder forecastBuilder = Forecast.builder();
            forecastRepository.findByDate(date).ifPresent(f -> forecastBuilder.id(f.getId()).createdAt(f.getCreatedAt()));
            forecastBuilder.date(date);
            Forecast forecast = forecastRepository.save(forecastBuilder.build());

            XMLStatement xmlDay = xmlForecast.getDay();
            ForecastStatement forecastStatement1 = forecastStatementRepository.save(constructForecastStatement(forecast, xmlDay, ForecastStatementType.DAY));
            for (XMLPlace xmlPlace : xmlDay.getPlaces()) {
                placeRepository.save(constructPlace(xmlPlace, forecastStatement1));
            }

            XMLStatement xmlNight = xmlForecast.getNight();
            ForecastStatement forecastStatement2 = forecastStatementRepository.save(constructForecastStatement(forecast, xmlNight, ForecastStatementType.NIGHT));
            for (XMLPlace xmlPlace : xmlNight.getPlaces()) {
                placeRepository.save(constructPlace(xmlPlace, forecastStatement2));
            }
        }
    }

    private Place constructPlace(XMLPlace xmlPlace, ForecastStatement forecastStatement) {
        Place.PlaceBuilder placeBuilder = Place.builder();
        placeRepository.findByForecastStatementAndName(forecastStatement, xmlPlace.getName()).ifPresent(s -> placeBuilder.id(s.getId()).createdAt(s.getCreatedAt()));
        placeBuilder.name(xmlPlace.getName());
        placeBuilder.maxTemperature(xmlPlace.getTempMax());
        placeBuilder.minTemperature(xmlPlace.getTempMin());
        placeBuilder.phenomenon(xmlPlace.getPhenomenon());
        placeBuilder.forecastStatement(forecastStatement);
        return placeBuilder.build();
    }

    private ForecastStatement constructForecastStatement(Forecast forecast, XMLStatement xmlStatement, ForecastStatementType type) {
        ForecastStatement.ForecastStatementBuilder forecastStatementBuilder = ForecastStatement.builder();
        forecastStatementRepository.findByForecastAndType(forecast, type).ifPresent(f -> forecastStatementBuilder.id(f.getId()).createdAt(f.getCreatedAt()));
        forecastStatementBuilder.phenomenon(xmlStatement.getPhenomenon());
        forecastStatementBuilder.statement(xmlStatement.getText());
        forecastStatementBuilder.type(type);
        forecastStatementBuilder.minTemperature(xmlStatement.getTempMin());
        forecastStatementBuilder.maxTemperature(xmlStatement.getTempMax());
        forecastStatementBuilder.forecast(forecast);
        return forecastStatementBuilder.build();
    }
}
