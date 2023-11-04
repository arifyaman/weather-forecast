package com.example.weathercheck.domain.forecast.service;

import com.example.weathercheck.client.impl.model.XMLForecast;
import com.example.weathercheck.client.impl.model.XMLForecasts;
import com.example.weathercheck.client.impl.model.XMLPlace;
import com.example.weathercheck.client.impl.model.XMLStatement;
import com.example.weathercheck.domain.forecast.api.v1.request.WeatherForecastRequest;
import com.example.weathercheck.domain.forecast.api.v1.response.PlaceForecastResponse;
import com.example.weathercheck.domain.forecast.api.v1.response.PlaceResponse;
import com.example.weathercheck.domain.forecast.api.v1.response.WeatherForecastResponse;
import com.example.weathercheck.domain.forecast.api.v1.response.WeatherForecastStatementResponse;
import com.example.weathercheck.domain.forecast.model.Forecast;
import com.example.weathercheck.domain.forecast.model.ForecastStatement;
import com.example.weathercheck.domain.forecast.model.Place;
import com.example.weathercheck.domain.forecast.model.PlaceStatement;
import com.example.weathercheck.domain.forecast.model.sub.ForecastStatementType;
import com.example.weathercheck.domain.forecast.repository.ForecastRepository;
import com.example.weathercheck.domain.forecast.repository.ForecastStatementRepository;
import com.example.weathercheck.domain.forecast.repository.PlaceRepository;
import com.example.weathercheck.domain.forecast.repository.PlaceStatementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherForecastService {
    private final ForecastRepository forecastRepository;
    private final ForecastStatementRepository forecastStatementRepository;
    private final PlaceStatementRepository placeStatementRepository;
    private final PlaceRepository placeRepository;

    public WeatherForecastResponse getForecast(WeatherForecastRequest request) {
        LocalDate start = Optional.ofNullable(request.start()).orElse(LocalDate.now());
        LocalDate end = Optional.ofNullable(request.end()).orElse(LocalDate.now().plusDays(4));

        if (request.placeId() == null) {
            List<ForecastStatement> statements = forecastStatementRepository.findForecastStatementForecasts(start, end);
            return toResponse(statements.stream().map(fs -> PlaceStatement.builder().forecastStatement(fs).build()).toList());
        } else {
            return toResponse(placeStatementRepository.findPlaceStatementForecasts(start, end, request.placeId()));
        }
    }

    private WeatherForecastResponse toResponse(List<PlaceStatement> placeStatements) {
        Map<Forecast, List<PlaceStatement>> groupedForecastStatements = placeStatements.stream()
                .collect(Collectors.groupingBy(placeStatement -> placeStatement.getForecastStatement().getForecast(), LinkedHashMap::new, Collectors.toList()));

        return new WeatherForecastResponse(
                groupedForecastStatements.entrySet().stream().map(s -> new WeatherForecastResponse.GroupedForecast(
                        s.getKey().getDate(),
                        s.getValue().stream().map(placeStatement -> new WeatherForecastStatementResponse(placeStatement.getForecastStatement().getType(),
                                placeStatement.getForecastStatement().getPhenomenon(),
                                placeStatement.getForecastStatement().getStatement(),
                                placeStatement.getForecastStatement().getMinTemperature(),
                                placeStatement.getForecastStatement().getMaxTemperature(),
                                toPlaceForecastResponse(placeStatement))).toList()
                )).toList()
        );
    }

    private PlaceForecastResponse toPlaceForecastResponse(PlaceStatement placeStatement) {
        if (placeStatement.getId() == null) return null;
        return new PlaceForecastResponse(
                placeStatement.getPlace().getName(),
                placeStatement.getPhenomenon(),
                placeStatement.getMinTemperature(),
                placeStatement.getMaxTemperature()
        );
    }

    public List<PlaceResponse> getPlaceNames() {
        return placeRepository.findAll().stream().map(p -> new PlaceResponse(p.getId(), p.getName())).toList();
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
                Place place = placeRepository.findByName(xmlPlace.getName()).orElseGet(() -> createPlace(xmlPlace));
                placeStatementRepository.save(constructPlaceStatement(xmlPlace, place, forecastStatement1));
            }

            XMLStatement xmlNight = xmlForecast.getNight();
            ForecastStatement forecastStatement2 = forecastStatementRepository.save(constructForecastStatement(forecast, xmlNight, ForecastStatementType.NIGHT));
            for (XMLPlace xmlPlace : xmlNight.getPlaces()) {
                Place place = placeRepository.findByName(xmlPlace.getName()).orElseGet(() -> createPlace(xmlPlace));
                placeStatementRepository.save(constructPlaceStatement(xmlPlace, place, forecastStatement2));
            }
        }
    }

    private Place createPlace(XMLPlace xmlPlace) {
        Place.PlaceBuilder builder = Place.builder().name(xmlPlace.getName());
        return placeRepository.save(builder.build());
    }

    private PlaceStatement constructPlaceStatement(XMLPlace xmlPlace, Place place, ForecastStatement forecastStatement) {
        PlaceStatement.PlaceStatementBuilder builder = PlaceStatement.builder();
        placeStatementRepository.findByForecastStatementAndPlace(forecastStatement, place).ifPresent(s -> builder.id(s.getId()).createdAt(s.getCreatedAt()));
        builder.maxTemperature(xmlPlace.getTempMax());
        builder.minTemperature(xmlPlace.getTempMin());
        builder.phenomenon(xmlPlace.getPhenomenon());
        builder.forecastStatement(forecastStatement);
        builder.place(place);
        return builder.build();
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
