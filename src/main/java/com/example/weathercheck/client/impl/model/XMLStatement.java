package com.example.weathercheck.client.impl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class XMLStatement {
    private String phenomenon;
    private Integer tempMin;
    private Integer tempMax;
    private String text;
    private List<XMLPlace> places = new ArrayList<>();

    @XmlElement(name = "phenomenon")
    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    @XmlElement(name = "tempmin")
    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    @XmlElement(name = "tempmax")
    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    @XmlElement(name = "text")
    public void setText(String text) {
        this.text = text;
    }

    @XmlElement(name = "place")
    public void setPlaces(List<XMLPlace> places) {
        this.places = places;
    }
}
