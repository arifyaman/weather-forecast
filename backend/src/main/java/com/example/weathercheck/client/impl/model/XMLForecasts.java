package com.example.weathercheck.client.impl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "forecasts")
@Getter
public class XMLForecasts {
    private List<XMLForecast> forecasts = new ArrayList<>();

    @XmlElement(name = "forecast")
    public void setForecasts(List<XMLForecast> forecasts) {
        this.forecasts = forecasts;
    }
}
